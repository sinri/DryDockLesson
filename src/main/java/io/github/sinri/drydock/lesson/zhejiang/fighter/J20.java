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
        router.route("/").handler(rc -> {
            getHttpServerLogger().info("J20 is serving " + rc.request().absoluteURI());
            rc.response().end("Hello World!")
              .onComplete(ar -> {
                  if (ar.failed()) {
                      getHttpServerLogger().exception(ar.cause(), "J20 failed to serve api");
                  } else {
                      getHttpServerLogger().info("J20 served api successfully");
                  }
              });
        });
    }
}
