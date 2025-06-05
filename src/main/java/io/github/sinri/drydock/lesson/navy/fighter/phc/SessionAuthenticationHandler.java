package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.AccountTableRow;
import io.github.sinri.keel.web.http.prehandler.KeelAuthenticationHandler;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class SessionAuthenticationHandler extends KeelAuthenticationHandler {
    private static final SessionAuthenticationHandler instance = new SessionAuthenticationHandler();

    public static SessionAuthenticationHandler getInstance() {
        return instance;
    }

    @Override
    protected Future<AuthenticateResult> handleRequest(RoutingContext routingContext) {
        return Future.succeededFuture()
                     .compose(v -> {
                         return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                             JsonObject body = routingContext.body().asJsonObject();
                             String token = body.getString("token");

                             AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                             return akagiAction.fetchSessionWithToken(token)
                                               .compose(row -> {
                                                   return akagiAction.fetchAccountWithId(row.getAccountId());
                                               })
                                               .compose(account -> {
                                                   if (account.getAccountStatus() == AccountTableRow.AccountStatusEnum.OFF) {
                                                       throw new RuntimeException("Account is OFF");
                                                   }
                                                   JsonObject principle = new JsonObject();
                                                   principle.put("account_id", account.getAccountId());
                                                   principle.put("account_name", account.getAccountName());
                                                   principle.put("display_name", account.getDisplayName());
                                                   principle.put("email", account.getEmail());
                                                   return Future.succeededFuture(principle);
                                               });
                         });
                     })
                     .compose(principle -> {
                         return Future.succeededFuture(AuthenticateResult.createAuthenticatedResult(principle));
                     }, throwable -> {
                         return Future.succeededFuture(AuthenticateResult.createAuthenticateFailedResult(throwable));
                     });
    }
}
