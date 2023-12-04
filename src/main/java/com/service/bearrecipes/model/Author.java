package com.service.bearrecipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "authors_name", nullable = false)
    private String name;

    @Column(name = "authors_lastname", nullable = false)
    private String lastName;


    public Author(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
