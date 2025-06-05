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
 * TABLE: api_client
 * (*￣∇￣*)
 * NOTICE BY KEEL:
 * To avoid being rewritten, do not modify this file manually, unless editable confirmed.
 * It was auto-generated on Thu Jun 05 18:44:11 CST 2025.
 *
 * @see io.github.sinri.keel.integration.mysql.dev.TableRowClassSourceCodeGenerator
 */
public class ApiClientTableRow extends AbstractTableRow {
    public static final String SCHEMA_AND_TABLE = "drydock_lesson.api_client";

    public ApiClientTableRow(JsonObject tableRow) {
        super(tableRow);
    }

    @Override
    @Nonnull
    public String sourceTableName() {
        return "api_client";
    }

    public String sourceSchemaName() {
        return "drydock_lesson";
    }

    /*
     * Field `client_id` of type `bigint(20) unsigned`.
     */
    @Nonnull
    public Long getClientId() {
        return Objects.requireNonNull(readLong("client_id"));
    }

    /*
     * Field `client_code` of type `varchar(128)`.
     */
    @Nonnull
    public String getClientCode() {
        return Objects.requireNonNull(readString("client_code"));
    }

    /*
     * Field `client_secret` of type `varchar(128)`.
     */
    @Nonnull
    public String getClientSecret() {
        return Objects.requireNonNull(readString("client_secret"));
    }

    /*
     * Enum{ON,OFF}
     *
     * Loose Enum of Field `status` of type `varchar(16)`.
     */
    @Nonnull
    public StatusEnum getStatus() {
        @Nullable String enumExpression = readString("status");
        Objects.requireNonNull(enumExpression, "The Enum Field `status` should not be null!");
        return StatusEnum.valueOf(enumExpression);
    }

    /**
     * Enum for Field `status`
     */
    public enum StatusEnum {
        ON,
        OFF,
    }


}

/*
CREATE TABLE `api_client` (
  `client_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `client_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `client_secret` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(16) NOT NULL DEFAULT 'ON' COMMENT 'Enum{ON,OFF}',
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `UK_CC` (`client_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */
