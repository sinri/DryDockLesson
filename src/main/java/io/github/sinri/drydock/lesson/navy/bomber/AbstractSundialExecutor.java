package io.github.sinri.drydock.lesson.navy.bomber;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.SundialTableRow;
import io.github.sinri.keel.core.cron.KeelCronExpression;
import io.github.sinri.keel.core.servant.sundial.KeelSundialPlan;

public abstract class AbstractSundialExecutor implements KeelSundialPlan {
    private final SundialTableRow sundialTableRow;

    public AbstractSundialExecutor(SundialTableRow sundialTableRow) {
        this.sundialTableRow = sundialTableRow;
    }

    @Override
    final public String key() {
        return sundialTableRow.getPlanId().toString();
    }

    @Override
    final public KeelCronExpression cronExpression() {
        return new KeelCronExpression(sundialTableRow.getCron());
    }

    protected final SundialTableRow getSundialTableRow() {
        return sundialTableRow;
    }
}
