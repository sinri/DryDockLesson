package io.github.sinri.drydock.lesson.receptionist.unfortified;

import io.github.sinri.drydock.lesson.navy.fighter.phc.UnfortifiedPHC;
import io.github.sinri.drydock.lesson.receptionist.AkagiReceptionist;
import io.github.sinri.keel.web.http.prehandler.PreHandlerChainMeta;
import io.vertx.ext.web.RoutingContext;

@PreHandlerChainMeta(UnfortifiedPHC.class)
public abstract class UnfortifiedAkagiReceptionist extends AkagiReceptionist {
    public UnfortifiedAkagiReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }
}
