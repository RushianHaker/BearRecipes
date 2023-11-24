package com.service.bearrecipes;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DbTestcontainersConfig.class})
class SQLTestContainerTest {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Test
    void searchForInsertData() {
    }

}
