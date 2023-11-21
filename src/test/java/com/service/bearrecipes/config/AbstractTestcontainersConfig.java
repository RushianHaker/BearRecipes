package com.service.bearrecipes.config;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
public abstract class AbstractTestcontainersConfig {

    @NotNull
    protected static DbInfo initPostgresDb(Network SHARED_NETWORK) {
        DbInfo.DbInfoBuilder builder = DbInfo.builder();

        //noinspection resource
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13")
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("postgres")
                .withNetwork(SHARED_NETWORK)
                .withNetworkAliases("db");

        container.start();

        DataSourceProperties properties = new DataSourceProperties();
        builder.properties(properties);

        properties.setDriverClassName("org.postgresql.Driver");
        properties.setUrl(container.getJdbcUrl());
        properties.setUsername(container.getUsername());
        properties.setPassword(container.getPassword());

        log.info("Postgres started at '" + container.getJdbcUrl() + "'. with " + container.getUsername() + "@" + container.getPassword());

        return builder.container(container).properties(properties).build();
    }

    @Builder
    @Getter
    public static class DbInfo {
        DataSourceProperties properties;
        PostgreSQLContainer<?> container;
    }
}