package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.AccountTableRow;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

public interface AccountActionMixin extends AkagiActionBase {
    default Future<AccountTableRow> fetchAccountWithId(long accountId) {
        return AnyStatement.select(s -> s
                                   .from(AccountTableRow.SCHEMA_AND_TABLE)
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsNumericValue("account_id", accountId)
                                   )
                           )
                           .queryForOneRow(getNamedSqlConnection(), AccountTableRow.class);
    }

    default Future<AccountTableRow> fetchAccountWithName(String accountName) {
        return AnyStatement.select(s -> s
                                   .from(AccountTableRow.SCHEMA_AND_TABLE)
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsLiteralValue("account_name", accountName)
                                   )
                           )
                           .queryForOneRow(getNamedSqlConnection(), AccountTableRow.class);
    }
}
