package io.github.sinri.drydock.lesson.receptionist.unfortified;

import io.github.sinri.keel.web.http.receptionist.ApiMeta;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

@ApiMeta(routePath = "/api/unfortified/sign-in")
public class SignInReceptionist extends UnfortifiedAkagiReceptionist {
    public SignInReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected Future<Object> handleForFuture() {
        // todo
        return Future.failedFuture("TODO");
    }
}
