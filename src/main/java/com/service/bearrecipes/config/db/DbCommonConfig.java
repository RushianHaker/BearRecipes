package com.service.bearrecipes.config.db;

import jakarta.validation.constraints.NotNull;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.sql.DataSource;

@Configuration
public class DbCommonConfig {
    private final Environment env;

    public DbCommonConfig(Environment env) {
        this.env = env;
    }

    @Bean("postgresDataSource")
    @Primary
    public DataSource postgresDataSource(@Qualifier("postgresDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    public SpringLiquibase postgresLiquibase(@Qualifier("postgresDataSource") DataSource dataSource,
                                        @Qualifier("postgresLiquibaseProperties") LiquibaseProperties properties) {
        return liquibaseConfigForProperties(dataSource, properties);
    }

    @Bean("postgresLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.liquibase.postgres")
    public LiquibaseProperties postgresLiquibaseProperties() {
        LiquibaseProperties properties = new LiquibaseProperties();
        /* Это основная база, развернём в неё данные тестов в рамках работ под соответствующим профилем спринга */
        properties.setContexts(env.acceptsProfiles(Profiles.of("junit")) ? "junit" : "real-db");
        return properties;
    }

    @NotNull
    private static SpringLiquibase liquibaseConfigForProperties(@NotNull DataSource dataSource, @NotNull LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setLiquibaseSchema(properties.getLiquibaseSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabelFilter(properties.getLabelFilter());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}