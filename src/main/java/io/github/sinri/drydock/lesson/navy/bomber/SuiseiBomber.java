package io.github.sinri.drydock.lesson.navy.bomber;

import io.github.sinri.drydock.aviation.aircraft.Bomber;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrierDeck;
import io.github.sinri.keel.core.servant.sundial.KeelSundialPlan;
import io.github.sinri.keel.core.servant.sundial.SundialIssueRecord;
import io.github.sinri.keel.logger.issue.recorder.KeelIssueRecorder;
import io.vertx.core.Future;

import javax.annotation.Nonnull;
import java.util.Collection;

public class SuiseiBomber extends Bomber {
    public SuiseiBomber(@Nonnull AircraftCarrierDeck deck) {
        super(deck);
    }

    @Override
    protected Future<Collection<KeelSundialPlan>> fetchSundialPlans(KeelIssueRecorder<SundialIssueRecord> sundialIssueRecorder) {
        return Future.succeededFuture(null);
    }
}
