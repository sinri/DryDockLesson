package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.AccountTableRow;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

import java.util.Objects;

import static io.github.sinri.keel.facade.KeelInstance.Keel;

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

    default Future<Long> generateAccount(
            String account_name,
            String display_name,
            String email,
            String password
    ) {
        return AnyStatement.insert(s -> s
                                   .intoTable(AccountTableRow.SCHEMA_AND_TABLE)
                                   .macroWriteOneRow(row -> row
                                           .put("account_name", account_name)
                                           .put("display_name", display_name)
                                           .put("email", email)
                                           .put("password_hash", Keel.authenticationHelper().php_password_hash(password))
                                           .put("account_status", AccountTableRow.AccountStatusEnum.ON.name())
                                   )
                           )
                           .executeForLastInsertedID(getNamedSqlConnection())
                           .compose(account_id -> {
                               Objects.requireNonNull(account_id);
                               return Future.succeededFuture(account_id);
                           });
    }
}
