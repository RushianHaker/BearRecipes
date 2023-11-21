package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByIngredientName(String ingredientName);
    Optional<Ingredient> findByPriceAndIngredientName(BigDecimal price, String ingredientName);
}
