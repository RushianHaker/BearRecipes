package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.dao.CountryRepository;
import com.service.bearrecipes.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TestEntityManager em;

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

        Country presentCountry = country.get();
        assertEquals(9999, presentCountry.getId());
        assertEquals("Test Country", presentCountry.getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var country = em.find(Country.class, 9999);

        assertEquals(9999, country.getId());
        assertEquals("Test Country", country.getName());

        countryRepository.deleteById(9999L);

        assertNull(em.find(Country.class, 9999));
    }
}
