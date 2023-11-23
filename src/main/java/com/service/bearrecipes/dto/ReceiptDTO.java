package com.service.bearrecipes.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.bearrecipes.model.Author;
import com.service.bearrecipes.model.Country;
import com.service.bearrecipes.model.Ingredient;
import com.service.bearrecipes.model.Receipt;
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

    private String plaintText;

    private Long complexity;

    private Author author;

    private Country country;

    @JsonIgnore
    private List<Ingredient> ingredients;


    public ReceiptDTO(String name, String plaintText, Long complexity, Author author, Country country, List<Ingredient> ingredients) {
        this.name = name;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
        this.ingredients = ingredients;
    }

    public ReceiptDTO(long id, String name, String plaintText, Long complexity, Author author, Country country) {
        this.id = id;
        this.name = name;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }

    public ReceiptDTO(String name, String plaintText, Long complexity, Author author, Country country) {
        this.name = name;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }


    public static ReceiptDTO toDto(Receipt book) {
        return new ReceiptDTO(book.getId(), book.getName(), book.getPlaintText(), book.getComplexity(), book.getAuthor(), book.getCountry());
    }

    public Receipt toDomainObject() {
        return new Receipt(id, name, plaintText, complexity, author, country,
                (ingredients != null && !ingredients.isEmpty()) ? new ArrayList<>(ingredients) : new ArrayList<>());
    }
}
