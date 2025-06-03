package io.github.sinri.drydock.lesson;

import io.github.sinri.drydock.lesson.navy.Akagi;
import io.github.sinri.drydock.lesson.navy.AkagiMySQLConnection;
import io.github.sinri.keel.integration.mysql.NamedMySQLDataSource;
import io.github.sinri.keel.logger.issue.center.KeelIssueRecordCenter;

public class Main {
    private static final Akagi akagi = new Akagi();

    public static void main(String[] args) {
        akagi.launch(args);
    }

    public static Akagi getAkagi() {
        return akagi;
    }

    public static KeelIssueRecordCenter getIssueRecordCenter() {
        return akagi.getIssueRecordCenter();
    }

    public static NamedMySQLDataSource<AkagiMySQLConnection> getMySQLDataSource() {
        return akagi.getMySQLDataSource();
    }

}