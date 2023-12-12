package com.service.bearrecipes.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title_image")
    private byte[] titleImage;

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

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt", fetch = FetchType.LAZY)
    private List<StepInfo> steps;


    public Receipt(String name, byte[] titleImage, String plaintText, Long complexity, Author author, Country country,
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

    public Receipt(long id, String name, byte[] titleImage, String plaintText, Long complexity, Author author,
                   Country country) {
        this.id = id;
        this.titleImage = titleImage;
        this.plaintText = plaintText;
        this.name = name;
        this.complexity = complexity;
        this.author = author;
        this.country = country;
    }

    public Receipt(String name, byte[] titleImage, String plaintText, Long complexity, Author author, Country country) {
        this.name = name;
        this.titleImage = titleImage;
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

    @JsonGetter("steps")
    @NotNull
    public List<StepInfo> orderedSteps() {
        if (steps == null) {
            return List.of();
        }
        SortedMap<Long, StepInfo> map = new TreeMap<>();
        steps.forEach(s -> map.put(s.getId(), s));
        return new ArrayList<>(map.values());
    }
}
