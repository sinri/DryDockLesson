package io.github.sinri.drydock.lesson.sundial;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.SundialTableRow;
import io.github.sinri.drydock.lesson.navy.bomber.AbstractSundialExecutor;
import io.github.sinri.keel.core.servant.sundial.SundialIssueRecord;
import io.github.sinri.keel.logger.issue.recorder.KeelIssueRecorder;
import io.vertx.core.Future;

import java.util.Calendar;

public class SampleExecutor extends AbstractSundialExecutor {
    public SampleExecutor(SundialTableRow sundialTableRow) {
        super(sundialTableRow);
    }

    @Override
    public Future<Void> execute(Calendar now, KeelIssueRecorder<SundialIssueRecord> sundialIssueRecorder) {
        sundialIssueRecorder.info("do some sample work");
        return Future.succeededFuture();
    }
}
