package io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson;

import io.github.sinri.keel.integration.mysql.result.row.AbstractTableRow;
import io.vertx.core.json.JsonObject;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Table comment is empty.
 * (´^ω^`)
 * SCHEMA: drydock_lesson
 * TABLE: queue_signal
 * (*￣∇￣*)
 * NOTICE BY KEEL:
 * To avoid being rewritten, do not modify this file manually, unless editable confirmed.
 * It was auto-generated on Thu Jun 05 18:44:11 CST 2025.
 *
 * @see io.github.sinri.keel.integration.mysql.dev.TableRowClassSourceCodeGenerator
 */
public class QueueSignalTableRow extends AbstractTableRow {
    public static final String SCHEMA_AND_TABLE = "drydock_lesson.queue_signal";

    public QueueSignalTableRow(JsonObject tableRow) {
        super(tableRow);
    }

    @Override
    @Nonnull
    public String sourceTableName() {
        return "queue_signal";
    }

    public String sourceSchemaName() {
        return "drydock_lesson";
    }

    /*
     * Field `signal_id` of type `bigint(20) unsigned`.
     */
    @Nonnull
    public Long getSignalId() {
        return Objects.requireNonNull(readLong("signal_id"));
    }

    /*
     * Field `signal` of type `varchar(16)`.
     */
    @Nonnull
    public String getSignal() {
        return Objects.requireNonNull(readString("signal"));
    }

    /*
     * Field `since` of type `int(11)`.
     */
    @Nonnull
    public Integer getSince() {
        return Objects.requireNonNull(readInteger("since"));
    }


}

/*
CREATE TABLE `queue_signal` (
  `signal_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `signal` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `since` int(11) NOT NULL,
  PRIMARY KEY (`signal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */
