package io.github.sinri.drydock.lesson.receptionist.session;

import io.github.sinri.drydock.lesson.navy.fighter.phc.AccountPrincipleWrapper;
import io.github.sinri.drydock.lesson.navy.fighter.phc.SessionPHC;
import io.github.sinri.drydock.lesson.receptionist.AkagiReceptionist;
import io.github.sinri.keel.web.http.prehandler.PreHandlerChainMeta;
import io.vertx.ext.web.RoutingContext;

@PreHandlerChainMeta(SessionPHC.class)
public abstract class SessionAkagiReceptionist extends AkagiReceptionist {
    public SessionAkagiReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }

    public AccountPrincipleWrapper readVisitor() {
        return new AccountPrincipleWrapper(readRequestUser().principal());
    }
}
