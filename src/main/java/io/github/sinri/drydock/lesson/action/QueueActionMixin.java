package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.QueueSignalTableRow;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.QueueTableRow;
import io.github.sinri.keel.core.servant.queue.KeelQueueSignal;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

public interface QueueActionMixin extends AkagiActionBase {
    default Future<KeelQueueSignal> fetchLatestQueueSignal() {
        return AnyStatement.select(s -> s
                                   .from(QueueSignalTableRow.SCHEMA_AND_TABLE)
                                   .orderByDesc("since")
                                   .limit(1)
                           )
                           .queryForOneRow(getNamedSqlConnection(), QueueSignalTableRow.class)
                           .compose(row -> {
                               KeelQueueSignal keelQueueSignal;
                               if (row == null) {
                                   keelQueueSignal = KeelQueueSignal.RUN;
                               } else {
                                   keelQueueSignal = KeelQueueSignal.valueOf(row.getSignal());
                               }
                               return Future.succeededFuture(keelQueueSignal);
                           });
    }

    default Future<QueueTableRow> fetchNextPendingQueueTask() {
        return AnyStatement.select(s -> s
                                   .from(QueueTableRow.SCHEMA_AND_TABLE)
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsLiteralValue("task_status", QueueTableRow.TaskStatusEnum.PENDING.name())
                                   )
                                   .orderByAsc("apply_time")
                                   .limit(1)
                           )
                           .queryForOneRow(getNamedSqlConnection(), QueueTableRow.class)
                ;
    }

    default Future<Void> declareQueueTaskStart(long task_id) {
        return AnyStatement.update(s -> s
                                   .table(QueueTableRow.SCHEMA_AND_TABLE)
                                   .setWithValue("task_status", QueueTableRow.TaskStatusEnum.RUNNING.name())
                                   .setWithExpression("start_time", "now()")
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsNumericValue("task_id", task_id)
                                           .expressionEqualsLiteralValue("task_status", QueueTableRow.TaskStatusEnum.PENDING.name())
                                   )
                                   .limit(1)
                           )
                           .executeForAffectedRows(getNamedSqlConnection())
                           .compose(afx -> {
                               if (afx != 1) {
                                   throw new RuntimeException("FAILED");
                               }
                               return Future.succeededFuture();
                           });
    }

    default Future<Void> declareQueueTaskEnd(long task_id, QueueTableRow.TaskStatusEnum finalTaskStatus, String feedback) {
        return AnyStatement.update(s -> s
                                   .table(QueueTableRow.SCHEMA_AND_TABLE)
                                   .setWithValue("task_status", finalTaskStatus.name())
                                   .setWithExpression("end_time", "now()")
                                   .setWithValue("feedback", feedback)
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsNumericValue("task_id", task_id)
                                           .expressionEqualsLiteralValue("task_status", QueueTableRow.TaskStatusEnum.RUNNING.name())
                                   )
                                   .limit(1)
                           )
                           .executeForAffectedRows(getNamedSqlConnection())
                           .compose(afx -> {
                               if (afx != 1) {
                                   throw new RuntimeException("FAILED");
                               }
                               return Future.succeededFuture();
                           });
    }
}
