package io.github.sinri.drydock.lesson.navy.drone;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.QueueTableRow;
import io.github.sinri.keel.core.servant.queue.KeelQueueTask;
import io.github.sinri.keel.logger.issue.center.KeelIssueRecordCenter;
import io.vertx.core.Future;

import javax.annotation.Nonnull;

public abstract class AbstractWorker extends KeelQueueTask {
    private final QueueTableRow queueTableRow;

    public AbstractWorker(QueueTableRow queueTableRow) {
        this.queueTableRow = queueTableRow;
    }

    protected final QueueTableRow getQueueTableRow() {
        return queueTableRow;
    }

    @Nonnull
    @Override
    final public String getTaskReference() {
        return queueTableRow.getTaskId().toString();
    }

    @Nonnull
    @Override
    final public String getTaskCategory() {
        return queueTableRow.getWorker();
    }

    @Override
    final protected KeelIssueRecordCenter getIssueRecordCenter() {
        return Main.getIssueRecordCenter();
    }

    @Override
    final protected Future<Void> run() {
        return Future.succeededFuture()
                     .compose(v -> {
                         return Main.getMySQLDataSource().withTransaction(akagiMySQLConnection -> {
                             AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                             return akagiAction.declareQueueTaskStart(queueTableRow.getTaskId());
                         });
                     })
                     .compose(v -> {
                         return work();
                     })
                     .compose(done -> {
                         return Main.getMySQLDataSource().withTransaction(akagiMySQLConnection -> {
                             AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                             return akagiAction.declareQueueTaskEnd(queueTableRow.getTaskId(), QueueTableRow.TaskStatusEnum.DONE, "DONE");
                         });
                     }, throwable -> {
                         return Main.getMySQLDataSource().withTransaction(akagiMySQLConnection -> {
                             AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                             return akagiAction.declareQueueTaskEnd(queueTableRow.getTaskId(), QueueTableRow.TaskStatusEnum.FAILED, throwable.toString());
                         });
                     });
    }

    abstract protected Future<Void> work();
}
