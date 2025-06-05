package io.github.sinri.drydock.lesson.navy;

import io.github.sinri.keel.integration.mysql.NamedMySQLConnection;
import io.vertx.sqlclient.SqlConnection;

import javax.annotation.Nonnull;

public class AkagiMySQLConnection extends NamedMySQLConnection {
    public final static String DATASOURCE_NAME = "akagi";

    public AkagiMySQLConnection(SqlConnection sqlConnection) {
        super(sqlConnection);
    }

    @Nonnull
    @Override
    public String getDataSourceName() {
        return DATASOURCE_NAME;
    }
}
