package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.SessionTableRow;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

public interface SessionActionMixin extends AkagiActionBase {
    default Future<SessionTableRow> fetchSessionWithToken(String token) {
        return AnyStatement
                .select(s -> s.from(SessionTableRow.SCHEMA_AND_TABLE)
                              .where(conditionsComponent -> conditionsComponent
                                      .expressionEqualsLiteralValue("token", token)
                                      .comparison(compare -> compare
                                              .compareExpression("start_time")
                                              .beLessThan().againstExpression("unix_timestamp()"))
                                      .comparison(compare -> compare
                                              .compareExpression("end_time")
                                              .beGreaterThan().againstExpression("unix_timestamp()"))))
                .queryForOneRow(getNamedSqlConnection(), SessionTableRow.class).compose(row -> {
                    if (row == null) {
                        return Future.failedFuture("Session Not Found");
                    }
                    return Future.succeededFuture(row);
                });
    }

    default Future<Void> createSessionForAccount(long account_id, String token, int start_time, int end_time) {
        return AnyStatement.insert(s -> s
                                   .intoTable(SessionTableRow.SCHEMA_AND_TABLE)
                                   .macroWriteOneRow(row -> row
                                           .put("token", token)
                                           .put("account_id", account_id)
                                           .put("start_time", start_time)
                                           .put("end_time", end_time)
                                   )
                           )
                           .executeForLastInsertedID(getNamedSqlConnection())
                           .compose(sessionId -> {
                               if (sessionId == null || sessionId <= 0) {
                                   return Future.failedFuture("Session Not Created");
                               }
                               return Future.succeededFuture();
                           });
    }
}
