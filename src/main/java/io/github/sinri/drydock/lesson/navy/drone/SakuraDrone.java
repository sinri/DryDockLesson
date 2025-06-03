package io.github.sinri.drydock.lesson.navy.drone;

import io.github.sinri.drydock.aviation.aircraft.Drone;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrierDeck;
import io.github.sinri.keel.core.servant.queue.KeelQueueSignal;
import io.github.sinri.keel.core.servant.queue.KeelQueueTask;
import io.vertx.core.Future;

import javax.annotation.Nonnull;

public class SakuraDrone extends Drone {
    public SakuraDrone(@Nonnull AircraftCarrierDeck deck) {
        super(deck);
    }

    @Override
    protected Future<KeelQueueSignal> readSignal() {
        return Future.succeededFuture(KeelQueueSignal.RUN);
    }

    @Override
    protected Future<KeelQueueTask> seekNextTask() {
        return Future.succeededFuture(null);
    }
}
