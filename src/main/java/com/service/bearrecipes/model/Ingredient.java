package com.service.bearrecipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "ingredient_name", nullable = false)
    private String ingredientName;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(targetEntity = Receipt.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    public Ingredient(String ingredientName, BigDecimal weight, BigDecimal price, Receipt receipt) {
        this.ingredientName = ingredientName;
        this.weight = weight;
        this.price = price;
        this.receipt = receipt;
    }

    public Ingredient(String ingredientName, BigDecimal weight, BigDecimal price) {
        this.ingredientName = ingredientName;
        this.weight = weight;
        this.price = price;
    }
}
