package io.github.sinri.drydock.lesson.entity.table.drydock_lesson.drydocklesson;

import io.github.sinri.keel.integration.mysql.result.row.AbstractTableRow;
import io.vertx.core.json.JsonObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Table comment is empty.
 * (´^ω^`)
 * SCHEMA: drydock_lesson
 * TABLE: project
 * (*￣∇￣*)
 * NOTICE BY KEEL:
 * To avoid being rewritten, do not modify this file manually, unless editable confirmed.
 * It was auto-generated on Tue Jun 03 11:35:18 CST 2025.
 *
 * @see io.github.sinri.keel.integration.mysql.dev.TableRowClassSourceCodeGenerator
 */
public class ProjectTableRow extends AbstractTableRow {
    public static final String SCHEMA_AND_TABLE = "drydock_lesson.project";

    public ProjectTableRow(JsonObject tableRow) {
        super(tableRow);
    }

    @Override
    @Nonnull
    public String sourceTableName() {
        return "project";
    }

    public String sourceSchemaName() {
        return "drydock_lesson";
    }

    /*
     * Field `project_id` of type `bigint(20) unsigned`.
     */
    @Nonnull
    public Long getProjectId() {
        return Objects.requireNonNull(readLong("project_id"));
    }

    /*
     * Field `project_name` of type `varchar(128)`.
     */
    @Nonnull
    public String getProjectName() {
        return Objects.requireNonNull(readString("project_name"));
    }

    /*
     * Enum{OPEN,FINISHED,CLOSED}
     *
     * Loose Enum of Field `project_status` of type `varchar(128)`.
     */
    @Nonnull
    public ProjectStatusEnum getProjectStatus() {
        @Nullable String enumExpression = readString("project_status");
        Objects.requireNonNull(enumExpression, "The Enum Field `project_status` should not be null!");
        return ProjectStatusEnum.valueOf(enumExpression);
    }

    /**
     * Enum for Field `project_status`
     */
    public enum ProjectStatusEnum {
        OPEN,
        FINISHED,
        CLOSED,
    }


}

/*
CREATE TABLE `project` (
  `project_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `project_status` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Enum{OPEN,FINISHED,CLOSED}',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC
 */
