package io.github.sinri.drydock.lesson.receptionist.open;

import io.github.sinri.drydock.lesson.navy.fighter.phc.OpenPHC;
import io.github.sinri.drydock.lesson.receptionist.AkagiReceptionist;
import io.github.sinri.keel.web.http.prehandler.PreHandlerChainMeta;
import io.vertx.ext.web.RoutingContext;

@PreHandlerChainMeta(OpenPHC.class)
public abstract class OpenAkagiReceptionist extends AkagiReceptionist {
    public OpenAkagiReceptionist(RoutingContext routingContext) {
        super(routingContext);
    }
}
