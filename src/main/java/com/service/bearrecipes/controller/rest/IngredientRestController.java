package com.service.bearrecipes.controller.rest;

import com.service.bearrecipes.dto.IngredientDTO;
import com.service.bearrecipes.model.Ingredient;
import com.service.bearrecipes.service.IngredientService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientRestController {
    private final IngredientService ingredientService;

    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/api/ingredient")
    public void addIngredient(@RequestBody @NotNull IngredientDTO ingredientDTO) {
        ingredientService.saveReceiptsIngredient(new Ingredient(ingredientDTO.getIngredientName(),
                ingredientDTO.getWeight(), ingredientDTO.getPrice(), ingredientDTO.getReceiptDTO().toDomainObject()));
    }

    @PostMapping("/api/ingredient/save_all")
    public void addListIngredients(@RequestBody @NotNull List<IngredientDTO> ingredients) {
        ingredientService.saveAllIngredientsDTO(ingredients);
    }

    @DeleteMapping("/api/ingredient/{ingredientId}")
    public void deleteIngredientById(@PathVariable("ingredientId") long ingredientId) {
        ingredientService.deleteIngredientById(ingredientId);
    }
}
