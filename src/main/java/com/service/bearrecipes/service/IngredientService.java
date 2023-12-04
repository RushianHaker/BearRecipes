package com.service.bearrecipes.service;


import com.service.bearrecipes.dto.IngredientDTO;
import com.service.bearrecipes.model.Ingredient;

import java.util.List;

public interface IngredientService {
    IngredientDTO findById(long ingredientId);

    List<Ingredient> findAll();

    Ingredient saveReceiptsIngredient(Ingredient ingredient);

    List<Ingredient> saveAllIngredients(List<Ingredient> ingredients);

    List<Ingredient> saveAllIngredientsDTO(List<IngredientDTO> ingredients);

    void deleteIngredientById(long id);
}
