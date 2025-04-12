package com.checkout.kata.integration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IntegrationTestDBConstant {

    public static final String DISABLE_FOREIGN_KEY_CHECK = "SET FOREIGN_KEY_CHECKS = 0";
    public static final String ENABLE_FOREIGN_KEY_CHECK = "SET FOREIGN_KEY_CHECKS = 1";
    public static final String TRUNCATE_TABLE = "TRUNCATE TABLE %s";
    public static final String SHOW_TABLES = "SHOW TABLES";
    public static final String FLY_SCHEMA_HISTORY = "flyway_schema_history";
}
