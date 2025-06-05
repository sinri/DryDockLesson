package io.github.sinri.drydock.lesson.receptionist.unfortified;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.AccountTableRow;
import io.github.sinri.keel.web.http.receptionist.AbstractRequestBody;
import io.github.sinri.keel.web.http.receptionist.ApiMeta;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

import static io.github.sinri.keel.facade.KeelInstance.Keel;

@ApiMeta(routePath = "/api/unfortified/sign-in")
public class SignInReceptionist extends UnfortifiedAkagiReceptionist {
    public SignInReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected Future<Object> handleForFuture() {
        RequestBody requestBody = new RequestBody(getRoutingContext());

        return Main.getMySQLDataSource().withTransaction(akagiMySQLConnection -> {
                       AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                       return akagiAction.fetchAccountWithName(requestBody.getAccountName())
                                         .compose(account -> {
                                             if (account.getAccountStatus() == AccountTableRow.AccountStatusEnum.OFF) {
                                                 throw new RuntimeException("Account OFF");
                                             }
                                             if (!Keel.authenticationHelper()
                                                      .php_password_verify(requestBody.getPassword(), account.getPasswordHash())) {
                                                 throw new RuntimeException("Password ERROR");
                                             }
                                             // create session
                                             String token = UUID.randomUUID().toString();
                                             int startTime = Math.toIntExact(System.currentTimeMillis() / 1000);
                                             int endTime = startTime + 8 * 3600;
                                             return akagiAction.createSessionForAccount(account.getAccountId(), token, startTime, endTime)
                                                               .compose(v -> {
                                                                   return Future.succeededFuture(new JsonObject()
                                                                           .put("token", token)
                                                                           .put("start_time", startTime)
                                                                           .put("end_time", endTime)
                                                                   );
                                                               });
                                         });
                   })
                   .recover(throwable -> Future.failedFuture(new Exception(throwable.getMessage())))
                   .compose(Future::succeededFuture);
    }

    private static class RequestBody extends AbstractRequestBody {

        public RequestBody(RoutingContext routingContext) {
            super(routingContext);
        }

        public String getAccountName() {
            return readString("account_name");
        }

        public String getPassword() {
            return readString("password");
        }
    }
}
