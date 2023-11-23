package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.IngredientRepository;
import com.service.bearrecipes.model.Ingredient;
import com.service.bearrecipes.service.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public Ingredient saveReceiptsIngredient(Ingredient ingredient) {
        var receipt = ingredient.getReceipt();

        if (receipt.getIngredients() == null) {
            receipt.setIngredients(new ArrayList<>());
            ingredient.setReceipt(receipt);
        }

        return ingredientRepository.save(ingredient);
    }

    //todo сделать сохранение и добавление рецепту ингридиентов списком, как это сделано с автором
    // также сделать удаление из списка + просто удаление из БД + поиск по id и названию

}
