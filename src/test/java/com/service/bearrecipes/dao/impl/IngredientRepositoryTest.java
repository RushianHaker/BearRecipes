package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.dao.IngredientRepository;
import com.service.bearrecipes.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class IngredientRepositoryTest {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private TestEntityManager em;

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
        var ingredient = em.find(Ingredient.class, 1);

        assertEquals(1, ingredient.getId());
        assertEquals("Test Ingredient", ingredient.getIngredientName());

        ingredientRepository.deleteById(1L);

        assertNull(em.find(Ingredient.class, 1));
    }
}
