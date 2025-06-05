package io.github.sinri.drydock.lesson.navy.fighter.phc;

import io.github.sinri.drydock.lesson.Main;
import io.github.sinri.drydock.lesson.action.AkagiAction;
import io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson.ApiClientTableRow;
import io.github.sinri.keel.web.http.prehandler.KeelAuthenticationHandler;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Objects;

import static io.github.sinri.keel.facade.KeelInstance.Keel;

/*
 * OpenAuthenticationHandler
 *
 * 该类继承自 KeelAuthenticationHandler，用于处理开放式客户端认证。
 * 主要流程：
 * 1. 从请求体中获取 client_code、nonce 和 checksum。
 * 2. 通过 client_code 查询数据库，获取对应的 ApiClientTableRow。
 * 3. 使用 client_code、nonce 和 client_secret 计算 MD5 校验和，并与传入的 checksum 比较。
 * 4. 校验通过后，构建 principle 信息，返回认证成功结果；否则抛出异常，返回认证失败结果。
 *
 * 适用于需要对外开放 API 的场景，通过简单的签名机制保证请求合法性。
 */
public class OpenAuthenticationHandler extends KeelAuthenticationHandler {

    private static final OpenAuthenticationHandler instance = new OpenAuthenticationHandler();

    /**
     * 获取 OpenAuthenticationHandler 单例实例。
     *
     * @return OpenAuthenticationHandler 实例
     */
    public static OpenAuthenticationHandler getInstance() {
        return instance;
    }

    /**
     * 校验 checksum 是否正确。
     * <p>
     * 校验方式：将 client_code、nonce 和 client_secret 拼接后做 MD5，
     * 与传入的 checksum 比较。
     * </p>
     *
     * @param row      ApiClientTableRow 数据库查询结果
     * @param nonce    随机数
     * @param checksum 校验和
     * @throws RuntimeException 校验失败时抛出
     */
    private static void judge(ApiClientTableRow row, String nonce, String checksum) {
        var raw = row.getClientCode() + "@" + nonce + row.getClientSecret();
        var expected = Keel.digestHelper().md5(raw);
        if (!Objects.equals(checksum, expected)) {
            throw new RuntimeException("Checksum Error");
        }
    }

    /**
     * 处理认证请求。
     * <p>
     * 认证流程：
     * 1. 解析请求体，获取 client_code。
     * 2. 查询数据库，获取对应的 ApiClientTableRow。
     * 3. 校验 checksum 是否正确。
     * 4. 校验通过后，返回 principle 信息。
     * </p>
     * @param routingContext 路由上下文，包含请求信息
     * @return Future<AuthenticateResult> 认证结果
     */
    @Override
    protected Future<AuthenticateResult> handleRequest(RoutingContext routingContext) {
        return Future.succeededFuture()
                .compose(v -> {
                    JsonObject body = routingContext.body().asJsonObject();
                    String clientCode = body.getString("client_code");

                    return Main.getMySQLDataSource().withConnection(akagiMySQLConnection -> {
                        AkagiAction akagiAction = new AkagiAction(akagiMySQLConnection);
                        return akagiAction.fetchApiClient(clientCode)
                                          .compose(row -> {
                                              String checksum = body.getString("checksum");
                                              String nonce = body.getString("nonce");

                                              judge(row, nonce, checksum);

                                              JsonObject principle = new JsonObject();
                                              principle.put("client_id", row.getClientId());
                                              principle.put("client_code", row.getClientCode());
                                              return Future.succeededFuture(principle);
                                          });
                    });
                })
                .compose(principle -> {
                    return Future.succeededFuture(AuthenticateResult.createAuthenticatedResult(principle));
                }, throwable -> {
                    return Future.succeededFuture(AuthenticateResult.createAuthenticateFailedResult(throwable));
                });

    }
}
