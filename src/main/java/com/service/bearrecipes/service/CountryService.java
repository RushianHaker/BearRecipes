package com.service.bearrecipes.service;


import com.service.bearrecipes.model.Country;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CountryService {
    Country findByName(@NotNull String name);

    List<Country> findAll();
}
