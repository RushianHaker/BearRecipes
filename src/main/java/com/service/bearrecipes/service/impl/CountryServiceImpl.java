package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.CountryRepository;
import com.service.bearrecipes.exception.CountryServiceException;
import com.service.bearrecipes.model.Country;
import com.service.bearrecipes.service.CountryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByName(@NotNull String countryName) {
        return countryRepository.findByName(countryName)
                .orElseThrow(() -> new CountryServiceException("Country not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
