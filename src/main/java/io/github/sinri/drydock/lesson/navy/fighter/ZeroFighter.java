package io.github.sinri.drydock.lesson.navy.fighter;

import io.github.sinri.drydock.aviation.aircraft.Fighter;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrierDeck;
import io.github.sinri.drydock.lesson.receptionist.AkagiReceptionist;
import io.github.sinri.keel.logger.event.KeelEventLog;
import io.github.sinri.keel.logger.issue.recorder.KeelIssueRecorder;
import io.github.sinri.keel.web.http.receptionist.KeelWebReceptionistLoader;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

import javax.annotation.Nonnull;

public class ZeroFighter extends Fighter {
    public ZeroFighter(@Nonnull AircraftCarrierDeck deck, int port) {
        super(deck, port);
    }

    @Override
    protected void configureHttpServerRoutes(Router router, KeelIssueRecorder<KeelEventLog> keelIssueRecorder) {
        KeelWebReceptionistLoader.loadPackage(router, "io.github.sinri.drydock.lesson.receptionist", AkagiReceptionist.class);
    }

    @Nonnull
    @Override
    protected Future<Void> beforeStartHttpServer() {
        return Future.succeededFuture();
    }
}
