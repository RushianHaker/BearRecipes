package com.service.bearrecipes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "receipts")
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column(name = "plain_text", nullable = false)
    private String plaintText;

    @Column(name = "complexity", nullable = false)
    private Long complexity;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt", fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;


    public Receipt(String name, String plaintText, Long complexity, Author author, Country country, List<Ingredient> ingredients) {
        this.name = name;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
        this.ingredients = ingredients;
    }

    public Receipt(long id, String name, String plaintText, Long complexity, Author author, Country country) {
        this.id = id;
        this.plaintText = plaintText;
        this.name = name;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }

    public Receipt(String name, String plaintText, Long complexity, Author author, Country country) {
        this.name = name;
        this.plaintText = plaintText;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }

    public Receipt(long id) {
        this.id = id;
    }

    public Receipt() {

    }
}
