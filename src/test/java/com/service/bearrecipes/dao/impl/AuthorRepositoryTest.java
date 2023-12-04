package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.dao.AuthorRepository;
import com.service.bearrecipes.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DbTestcontainersConfig.class})
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findById() {
        var author = authorRepository.findById(1L);

        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("TestAuthor", presentAuthor.getName());
        assertEquals("TestAuthor", presentAuthor.getLastName());
    }

    @Test
    void findByNameAndLastNameAndCountry() {
        var author = authorRepository.findByNameAndLastName("TestAuthor", "TestAuthor");

        assertNotNull(author);

        Author dbAuthor = author.orElseThrow();
        assertEquals(1, dbAuthor.getId());
        assertEquals("TestAuthor", dbAuthor.getName());
        assertEquals("TestAuthor", dbAuthor.getLastName());
    }

    @Test
    void save() {
        var saveAuthor = authorRepository.save(new Author("AAAAAA","AAAAAA"));

        var dbAuthor = authorRepository.findById(2L).orElseThrow();

        assertEquals(dbAuthor.getId(), saveAuthor.getId());
        assertEquals(dbAuthor.getName(), saveAuthor.getName());
        assertEquals(dbAuthor.getLastName(), saveAuthor.getLastName());
    }
}
