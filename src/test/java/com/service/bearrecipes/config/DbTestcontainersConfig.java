package com.service.bearrecipes.config;


import org.junit.ClassRule;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

@ActiveProfiles(profiles = {"junit"})
@TestConfiguration()
public class DbTestcontainersConfig extends AbstractTestcontainersConfig {
    @ClassRule
    public static final PostgreSQLContainer<?> postgresContainer;

    private static final DataSourceProperties properties;

    static {
        try {
            DbInfo bearrecipesDb = initPostgresDb(Network.newNetwork());
            postgresContainer = bearrecipesDb.getContainer();
            properties = bearrecipesDb.getProperties();
        } catch (Exception e) {
            throw new RuntimeException("Error in process of creation Testcontainers config", e);
        }
    }

    @Bean("bearrecipesDataSourceProperties")
    @Primary
    public DataSourceProperties bearrecipesDataSourceProperties() {
        return properties;
    }
}