package io.github.sinri.drydock.lesson.zhejiang.fighter;

import io.github.sinri.drydock.aviation.Fighter;
import io.github.sinri.drydock.naval.carrier.AircraftCarrierDeck;
import io.vertx.ext.web.Router;

import javax.annotation.Nonnull;

public class J20 extends Fighter {
    public J20(@Nonnull AircraftCarrierDeck deck, int port) {
        super(deck, port);
    }

    @Override
    protected void configureRoutes(Router router) {
        router.route("/").handler(rc -> rc.response().end("Hello World!"));
    }
}
