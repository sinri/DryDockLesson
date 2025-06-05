package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.OrganizationTableRow;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

import java.util.List;

public interface OrganizationActionMixin extends AkagiActionBase {
    default Future<List<OrganizationTableRow>> fetchOrganizationList() {
        return AnyStatement.select(s -> s
                                   .from(OrganizationTableRow.SCHEMA_AND_TABLE)
                           )
                           .queryForRowList(getNamedSqlConnection(), OrganizationTableRow.class);
    }
}
