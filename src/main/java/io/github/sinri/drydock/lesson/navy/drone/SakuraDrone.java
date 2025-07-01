package io.github.sinri.drydock.lesson.navy.drone;

import io.github.sinri.drydock.aviation.aircraft.Drone;
import io.github.sinri.drydock.aviation.carrier.AircraftCarrierDeck;
import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.QueueTableRow;
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
        return Main.getMySQLDataSource()
                   .withConnection(akagiMySQLConnection -> new AkagiAction(akagiMySQLConnection)
                           .fetchLatestQueueSignal());
    }

    @Override
    protected Future<KeelQueueTask> seekNextTask() {
        return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                       return new AkagiAction(akagiMySQLConnection).fetchNextPendingQueueTask();
                   })
                   .compose(row -> {
                       if (row == null) {
                           return Future.succeededFuture(null);
                       }

                       String className = "io.github.sinri.drydock.lesson.queue." + row.getWorker();
                       try {
                           AbstractWorker worker = (AbstractWorker) Class.forName(className)
                                                                         .getConstructor(QueueTableRow.class)
                                                                         .newInstance(row);
                           return Future.succeededFuture(worker);
                       } catch (Throwable e) {
                           return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                               var akagiAction = new AkagiAction(akagiMySQLConnection);
                               return akagiAction.declareQueueTaskStart(row.getTaskId())
                                                 .compose(v -> {
                                                     return akagiAction.declareQueueTaskEnd(row.getTaskId(), QueueTableRow.TaskStatusEnum.FAILED, e.toString());
                                                 })
                                                 .compose(v -> {
                                                     return Future.succeededFuture(null);
                                                 });
                           });
                       }
                   });

    }
}
