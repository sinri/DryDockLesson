package io.github.sinri.drydock.lesson.receptionist.session;

import io.github.sinri.drydock.lesson.navy.fighter.phc.AccountPrincipleWrapper;
import io.github.sinri.keel.web.http.receptionist.ApiMeta;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

@ApiMeta(routePath = "/api/session/get-my-account-info")
public class GetMyAccountInfoReceptionist extends SessionAkagiReceptionist {
    public GetMyAccountInfoReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected Future<Object> handleForFuture() {
        return Future.succeededFuture()
                     .compose(v -> {
                         AccountPrincipleWrapper visitor = readVisitor();
                         return Future.succeededFuture(visitor.cloneAsJsonObject());
                     });
    }
}
