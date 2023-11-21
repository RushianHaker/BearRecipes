package com.service.bearrecipes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "country_name", nullable = false)
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {

    }
}
