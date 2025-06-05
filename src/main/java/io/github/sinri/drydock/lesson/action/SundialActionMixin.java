package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.SundialTableRow;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

import java.util.List;

public interface SundialActionMixin extends AkagiActionBase {
    default Future<List<SundialTableRow>> fetchSundialPlans() {
        return AnyStatement.select(s -> s
                                   .from(SundialTableRow.SCHEMA_AND_TABLE)
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsLiteralValue("switch", SundialTableRow.SwitchEnum.ON.name())
                                   )
                           )
                           .queryForRowList(getNamedSqlConnection(), SundialTableRow.class);
    }
}
