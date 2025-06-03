package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.keel.web.http.prehandler.KeelAuthenticationHandler;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class OpenAuthenticationHandler extends KeelAuthenticationHandler {

    private static final OpenAuthenticationHandler instance = new OpenAuthenticationHandler();

    public static OpenAuthenticationHandler getInstance() {
        return instance;
    }

    @Override
    protected Future<AuthenticateResult> handleRequest(RoutingContext routingContext) {
        return Future.succeededFuture()
                .compose(v -> {
                    return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                        // todo make it work
                        JsonObject principle = new JsonObject();

                        return Future.succeededFuture(principle);
                    });
                })
                .compose(principle -> {
                    return Future.succeededFuture(AuthenticateResult.createAuthenticatedResult(principle));
                }, throwable -> {
                    return Future.succeededFuture(AuthenticateResult.createAuthenticateFailedResult(throwable));
                });

    }
}
