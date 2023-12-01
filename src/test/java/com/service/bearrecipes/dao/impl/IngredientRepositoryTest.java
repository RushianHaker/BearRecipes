package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.dao.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DbTestcontainersConfig.class})
class IngredientRepositoryTest {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    void findById() {
        var ingredient = ingredientRepository.findById(1L);

        assertTrue(ingredient.isPresent());

        var presentIngredient = ingredient.get();
        assertEquals(1, presentIngredient.getId());
        assertEquals("Test Ingredient", presentIngredient.getIngredientName());
    }

    @Test
    @Rollback
    void deleteById() {
        var ingredient = ingredientRepository.findById(1L).orElseThrow();

        assertEquals(1, ingredient.getId());
        assertEquals("Test Ingredient", ingredient.getIngredientName());

        ingredientRepository.deleteById(1L);

        assertFalse(ingredientRepository.findById(1L).isPresent());
    }
}
