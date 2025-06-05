package io.github.sinri.drydock.lesson.entity.table.akagi.drydocklesson;

import io.github.sinri.keel.integration.mysql.result.row.AbstractTableRow;
import io.vertx.core.json.JsonObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Table comment is empty.
 * (´^ω^`)
 * SCHEMA: drydock_lesson
 * TABLE: queue
 * (*￣∇￣*)
 * NOTICE BY KEEL:
 * To avoid being rewritten, do not modify this file manually, unless editable confirmed.
 * It was auto-generated on Thu Jun 05 18:44:11 CST 2025.
 *
 * @see io.github.sinri.keel.integration.mysql.dev.TableRowClassSourceCodeGenerator
 */
public class QueueTableRow extends AbstractTableRow {
    public static final String SCHEMA_AND_TABLE = "drydock_lesson.queue";

    public QueueTableRow(JsonObject tableRow) {
        super(tableRow);
    }

    @Override
    @Nonnull
    public String sourceTableName() {
        return "queue";
    }

    public String sourceSchemaName() {
        return "drydock_lesson";
    }

    /*
     * Field `task_id` of type `bigint(20) unsigned`.
     */
    @Nonnull
    public Long getTaskId() {
        return Objects.requireNonNull(readLong("task_id"));
    }

    /*
     * Field `worker` of type `varchar(128)`.
     */
    @Nonnull
    public String getWorker() {
        return Objects.requireNonNull(readString("worker"));
    }

    /*
     * A JSON OBJECT
     *
     * Field `param` of type `text`.
     */
    @Nonnull
    public String getParam() {
        return Objects.requireNonNull(readString("param"));
    }

    /*
     * Enum{PENDING,RUNNING,DONE,FAILED}
     *
     * Loose Enum of Field `task_status` of type `varchar(16)`.
     */
    @Nonnull
    public TaskStatusEnum getTaskStatus() {
        @Nullable String enumExpression = readString("task_status");
        Objects.requireNonNull(enumExpression, "The Enum Field `task_status` should not be null!");
        return TaskStatusEnum.valueOf(enumExpression);
    }

    /*
     * Field `feedback` of type `text`.
     */
    @Nullable
    public String getFeedback() {
        return readString("feedback");
    }

    /*
     * Field `apply_time` of type `datetime`.
     */
    @Nonnull
    public String getApplyTime() {
        return Objects.requireNonNull(readDateTime("apply_time"));
    }

    /*
     * Field `start_time` of type `datetime`.
     */
    @Nullable
    public String getStartTime() {
        return readDateTime("start_time");
    }

    /*
     * Field `end_time` of type `datetime`.
     */
    @Nullable
    public String getEndTime() {
        return readDateTime("end_time");
    }

    /**
     * Enum for Field `task_status`
     */
    public enum TaskStatusEnum {
        PENDING,
        RUNNING,
        DONE,
        FAILED,
    }


}

/*
CREATE TABLE `queue` (
  `task_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `worker` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'A JSON OBJECT',
  `task_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Enum{PENDING,RUNNING,DONE,FAILED}',
  `feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `apply_time` datetime NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */
