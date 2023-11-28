package com.service.bearrecipes.dto;


import com.service.bearrecipes.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private long id;

    private String ingredientName;

    private BigDecimal weight;

    private BigDecimal price;

    private ReceiptDTO receiptDTO;

    public IngredientDTO(String ingredientName, BigDecimal weight, BigDecimal price, ReceiptDTO receiptDTO) {
        this.ingredientName = ingredientName;
        this.weight = weight;
        this.price = price;
        this.receiptDTO = receiptDTO;
    }

    public static IngredientDTO toDto(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getId(), ingredient.getIngredientName(),
                ingredient.getWeight(), ingredient.getPrice(), ReceiptDTO.toDto(ingredient.getReceipt()));
    }
}
