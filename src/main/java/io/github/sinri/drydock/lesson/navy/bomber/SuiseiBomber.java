package io.github.sinri.drydock.lesson.navy.bomber;

import io.github.sinri.drydock.aviation.aircraft.Bomber;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrierDeck;
import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.SundialTableRow;
import io.github.sinri.keel.core.servant.sundial.KeelSundialPlan;
import io.github.sinri.keel.core.servant.sundial.SundialIssueRecord;
import io.github.sinri.keel.logger.issue.recorder.KeelIssueRecorder;
import io.vertx.core.Future;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SuiseiBomber extends Bomber {
    public SuiseiBomber(@Nonnull AircraftCarrierDeck deck) {
        super(deck);
    }

    @Override
    protected Future<Collection<KeelSundialPlan>> fetchSundialPlans(KeelIssueRecorder<SundialIssueRecord> sundialIssueRecorder) {
        return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                       AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                       return akagiAction.fetchSundialPlans();
                   })
                   .compose(sundialTableRows -> {
                       List<KeelSundialPlan> list = new ArrayList<>();

                       for (var row : sundialTableRows) {
                           String executor = row.getExecutor();
                           String className = "io.github.sinri.drydock.lesson.sundial." + executor;
                           try {
                               AbstractSundialExecutor sundialExecutor = (AbstractSundialExecutor) Class.forName(className)
                                                                                                        .getConstructor(SundialTableRow.class)
                                                                                                        .newInstance(row);
                               list.add(sundialExecutor);
                           } catch (Throwable e) {
                               getUnitLogger().exception(e);
                           }
                       }

                       return Future.succeededFuture(list);
                   });
    }


}
