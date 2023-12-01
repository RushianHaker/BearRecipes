package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.dao.CountryRepository;
import com.service.bearrecipes.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DbTestcontainersConfig.class})
class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;

    @Test
    void findById() {
        var country = countryRepository.findById(9999L);

        assertTrue(country.isPresent());

        var presentCountry = country.get();
        assertEquals(9999, presentCountry.getId());
        assertEquals("Test Country", presentCountry.getName());
    }

    @Test
    void findByName() {
        var country = countryRepository.findByName("Test Country");

        assertNotNull(country);

        Country presentCountry = country.orElseThrow();
        assertEquals(9999, presentCountry.getId());
        assertEquals("Test Country", presentCountry.getName());
    }
}
