package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Country;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(@NotNull String name);
}
