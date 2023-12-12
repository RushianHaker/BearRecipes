package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.IngredientRepository;
import com.service.bearrecipes.dto.IngredientDTO;
import com.service.bearrecipes.exception.ReceiptServiceException;
import com.service.bearrecipes.model.Ingredient;
import com.service.bearrecipes.service.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public IngredientDTO findById(long ingredientId) {
        return IngredientDTO.toDto(ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ReceiptServiceException("Ingredient not found!")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
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

    @Override
    @Transactional
    public List<Ingredient> saveAllIngredients(List<Ingredient> ingredients) {
        ingredients.stream()
                .filter(ingredient -> ingredient.getReceipt().getIngredients() == null)
                .forEach(ingredient -> {
                    var receipt = ingredient.getReceipt();
                    receipt.setIngredients(new ArrayList<>());
                    ingredient.setReceipt(receipt);
                });

        return ingredientRepository.saveAll(ingredients);
    }

    @Override
    @Transactional
    public List<Ingredient> saveAllIngredientsDTO(List<IngredientDTO> ingredients) {
        var resultList = new ArrayList<Ingredient>();

        ingredients.forEach(ingredientDTO -> resultList.add(new Ingredient(ingredientDTO.getIngredientName(),
                ingredientDTO.getWeight(), ingredientDTO.getPrice(), ingredientDTO.getReceiptDTO().toDomainObject())));

        return saveAllIngredients(resultList);
    }

    @Override
    public void deleteIngredientById(long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
