package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Author;
import com.service.bearrecipes.model.Country;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndLastNameAndCountry(@NotNull String name, @NotNull String lastName, @NotNull Country country);
}
