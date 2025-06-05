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
 * TABLE: sundial
 * (*￣∇￣*)
 * NOTICE BY KEEL:
 * To avoid being rewritten, do not modify this file manually, unless editable confirmed.
 * It was auto-generated on Thu Jun 05 18:44:11 CST 2025.
 *
 * @see io.github.sinri.keel.integration.mysql.dev.TableRowClassSourceCodeGenerator
 */
public class SundialTableRow extends AbstractTableRow {
    public static final String SCHEMA_AND_TABLE = "drydock_lesson.sundial";

    public SundialTableRow(JsonObject tableRow) {
        super(tableRow);
    }

    @Override
    @Nonnull
    public String sourceTableName() {
        return "sundial";
    }

    public String sourceSchemaName() {
        return "drydock_lesson";
    }

    /*
     * Field `plan_id` of type `bigint(20) unsigned`.
     */
    @Nonnull
    public Long getPlanId() {
        return Objects.requireNonNull(readLong("plan_id"));
    }

    /*
     * Field `executor` of type `varchar(128)`.
     */
    @Nonnull
    public String getExecutor() {
        return Objects.requireNonNull(readString("executor"));
    }

    /*
     * Field `description` of type `varchar(128)`.
     */
    @Nonnull
    public String getDescription() {
        return Objects.requireNonNull(readString("description"));
    }

    /*
     * Field `cron` of type `varchar(128)`.
     */
    @Nonnull
    public String getCron() {
        return Objects.requireNonNull(readString("cron"));
    }

    /*
     * Enum{ON,OFF}
     *
     * Loose Enum of Field `switch` of type `varchar(8)`.
     */
    @Nonnull
    public SwitchEnum getSwitch() {
        @Nullable String enumExpression = readString("switch");
        Objects.requireNonNull(enumExpression, "The Enum Field `switch` should not be null!");
        return SwitchEnum.valueOf(enumExpression);
    }

    /**
     * Enum for Field `switch`
     */
    public enum SwitchEnum {
        ON,
        OFF,
    }


}

/*
CREATE TABLE `sundial` (
  `plan_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `executor` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cron` varchar(128) NOT NULL,
  `switch` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Enum{ON,OFF}',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */
