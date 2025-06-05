package io.github.sinri.drydock.lesson.queue;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.QueueTableRow;
import io.github.sinri.drydock.lesson.navy.drone.AbstractWorker;
import io.vertx.core.Future;

public class SampleWorker extends AbstractWorker {
    public SampleWorker(QueueTableRow queueTableRow) {
        super(queueTableRow);
    }

    @Override
    protected Future<Void> work() {
        getQueueTaskIssueRecorder().info("do some sample work");
        return Future.succeededFuture();
    }
}
