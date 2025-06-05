package io.github.sinri.drydock.lesson.action;

import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.ApiClientTableRow;
import io.github.sinri.keel.integration.mysql.statement.AnyStatement;
import io.vertx.core.Future;

public interface ApiClientActionMixin extends AkagiActionBase {
    /**
     * 根据客户端代码获取有效的API客户端信息
     *
     * @param clientCode 客户端代码
     * @return 返回包含API客户端信息的Future对象，如果未找到有效的客户端则返回失败
     */
    default Future<ApiClientTableRow> fetchApiClient(String clientCode) {
        return AnyStatement.select(s -> s
                                   .from(ApiClientTableRow.SCHEMA_AND_TABLE)
                                   .where(conditionsComponent -> conditionsComponent
                                           .expressionEqualsLiteralValue("client_code", clientCode)
                                           .expressionEqualsLiteralValue("status", ApiClientTableRow.StatusEnum.ON.name())
                                   )
                           )
                           .queryForOneRow(getNamedSqlConnection(), ApiClientTableRow.class)
                           .compose(row -> {
                               if (row == null) {
                                   return Future.failedFuture("无有效的API CLIENT在库");
                               }
                               return Future.succeededFuture(row);
                           });
    }
}
