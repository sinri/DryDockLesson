package io.github.sinri.drydock.lesson.zhejiang.fighter;

import io.github.sinri.drydock.aviation.aircraft.Fighter;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrierDeck;
import io.github.sinri.keel.logger.event.KeelEventLog;
import io.github.sinri.keel.logger.issue.recorder.KeelIssueRecorder;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

import javax.annotation.Nonnull;

public class J20 extends Fighter {
    public J20(@Nonnull AircraftCarrierDeck deck, int port) {
        super(deck, port);
    }

    @Override
    protected void configureHttpServerRoutes(Router router, KeelIssueRecorder<KeelEventLog> httpServerLogger) {
        router.route("/").handler(rc -> rc.response().end("Hello World!"));
    }

    @Nonnull
    @Override
    protected Future<Void> beforeStartHttpServer() {
        return Future.succeededFuture();
    }
}
