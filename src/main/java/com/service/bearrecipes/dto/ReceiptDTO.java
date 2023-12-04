package com.service.bearrecipes.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.bearrecipes.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private long id;

    private String name;

    private byte[] titleImage;

    private String plaintText;

    private Long complexity;

    private Author author;

    private Country country;

    @JsonIgnore
    private List<Ingredient> ingredients;

    @JsonIgnore
    private List<StepInfo> steps;


    public ReceiptDTO(String name, byte[] titleImage, String plaintText, Long complexity, Author author, Country country,
                      List<Ingredient> ingredients, List<StepInfo> steps) {
        this.name = name;
        this.titleImage = titleImage;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public ReceiptDTO(long id, String name, byte[] titleImage, String plaintText, Long complexity, Author author,
                      Country country) {
        this.id = id;
        this.titleImage = titleImage;
        this.name = name;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }

    public ReceiptDTO(String name, byte[] titleImage, String plaintText, Long complexity, Author author,
                      Country country) {
        this.name = name;
        this.titleImage = titleImage;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }


    public static ReceiptDTO toDto(Receipt receipt) {
        return new ReceiptDTO(receipt.getId(), receipt.getName(), receipt.getTitleImage(), receipt.getPlaintText(),
                receipt.getComplexity(), receipt.getAuthor(), receipt.getCountry());
    }

    public Receipt toDomainObject() {
        return new Receipt(id, name, titleImage, plaintText, complexity, author, country,
                (ingredients != null && !ingredients.isEmpty()) ? new ArrayList<>(ingredients) : new ArrayList<>(),
                (steps != null && !steps.isEmpty()) ? new ArrayList<>(steps) : new ArrayList<>());
    }
}
