package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Author;
import com.service.bearrecipes.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndLastNameAndCountry(String name, String lastName, Country country);
}
