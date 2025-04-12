package com.checkout.kata.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationKey {

    public static final String PRIMARY_DATASOURCE="spring.datasource.primary";
}
