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
        return Main.getMySQLDataSource().withTransaction(akagiMySQLConnection -> {
            AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);

            return akagiAction.fetchNextPendingQueueTask()
                              .compose(row -> {
                                  if (row == null) {
                                      return Future.succeededFuture(null);
                                  }
                                  return Future.succeededFuture(row);
                              })
                              .compose(row -> {
                                  String className = "io.github.sinri.drydock.lesson.queue." + row.getWorker();
                                  try {
                                      AbstractWorker worker = (AbstractWorker) Class.forName(className)
                                                                                    .getConstructor(QueueTableRow.class)
                                                                                    .newInstance(row);
                                      return Future.succeededFuture(worker);
                                  } catch (Throwable e) {
                                      return akagiAction.declareQueueTaskStart(row.getTaskId())
                                                        .compose(v -> {
                                                            return akagiAction.declareQueueTaskEnd(row.getTaskId(), QueueTableRow.TaskStatusEnum.FAILED, e.toString());
                                                        })
                                                        .compose(v -> {
                                                            return Future.succeededFuture(null);
                                                        });
                                  }
                              });
        });
    }
}
