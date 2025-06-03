package io.github.sinri.drydock.lesson.test;

import io.github.sinri.drydock.lesson.navy.AkagiMySQLConnection;
import io.github.sinri.drydock.naval.raider.ClassFileGeneratorForMySQLTables;
import io.github.sinri.keel.facade.tesuto.instant.InstantRunUnit;
import io.vertx.core.Future;

import javax.annotation.Nullable;

public class MySQLTableClassGenerator extends ClassFileGeneratorForMySQLTables {
    @Override
    protected String getTablePackage() {
        return "io.github.sinri.drydock.lesson.entity.table";
    }

    @Nullable
    @Override
    public String getStrictEnumPackage() {
        return "io.github.sinri.drydock.lesson.entity.enums";
    }

    @Nullable
    @Override
    public String getEnvelopePackage() {
        return "io.github.sinri.drydock.lesson.entity.envelope";
    }

    @InstantRunUnit
    public Future<Void> generate() {
        return this.rebuildTablesInSchema(AkagiMySQLConnection.DATASOURCE_NAME, AkagiMySQLConnection::new, "drydock_lesson");
    }
}
