package com.service.bearrecipes.config.db;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!junit")
public class DbPropertiesConfig {

    @Bean("bearrecipesDataSourceProperties")
    @Primary
    @ConfigurationProperties("spring.datasource.bearrecipes")
    public DataSourceProperties bearrecipesDataSourceProperties() {
        return new DataSourceProperties();
    }
}