package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.AuthorRepository;
import com.service.bearrecipes.dao.CountryRepository;
import com.service.bearrecipes.dao.ReceiptRepository;
import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.exception.ReceiptServiceException;
import com.service.bearrecipes.model.Author;
import com.service.bearrecipes.model.Ingredient;
import com.service.bearrecipes.model.Receipt;
import com.service.bearrecipes.service.ReceiptService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, AuthorRepository authorRepository,
                              CountryRepository countryRepository) {
        this.receiptRepository = receiptRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ReceiptDTO findById(long receiptId) {
        var receiptInfo = receiptRepository.findById(receiptId).orElseThrow(() -> new ReceiptServiceException("Receipt not found!"));

        List<Ingredient> ingredients = receiptInfo.getIngredients() == null ? new ArrayList<>() : receiptInfo.getIngredients().stream()
                .filter(ingredient -> ingredient.getIngredientName() != null && !ingredient.getIngredientName().isEmpty())
                .toList();

        return new ReceiptDTO(receiptInfo.getId(), receiptInfo.getName(), receiptInfo.getPlaintText(),
                receiptInfo.getComplexity(), receiptInfo.getAuthor(), receiptInfo.getCountry(), ingredients);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceiptDTO> findAll() {
        return receiptRepository.findAll().stream().map(ReceiptDTO::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Receipt saveOrUpdate(@NotNull ReceiptDTO receiptDTO) {
        var receiptName = receiptDTO.getName();
        var receiptPlaintText = receiptDTO.getPlaintText();
        var receiptComplexity = receiptDTO.getComplexity();
        var receiptCountry = receiptDTO.getCountry();

        if ((receiptName == null || receiptName.isEmpty()) || (receiptPlaintText == null || receiptPlaintText.isEmpty())
                || receiptComplexity == null || receiptCountry == null) {
            throw new ReceiptServiceException("Receipt name/plaintText/complexity/country can't be null or empty!");
        }

        var receiptCountryFromDB = countryRepository.findByName(receiptCountry.getName())
                .orElseThrow(() -> new ReceiptServiceException("Cant find receipt country: " + receiptCountry.getName()));

        var receiptForSave = new Receipt(receiptName, receiptPlaintText, receiptComplexity,
                findAuthorOrSaveAndReturn(receiptDTO.getAuthor()), receiptCountryFromDB);

        var receiptId = receiptDTO.getId();
        if (receiptId != 0) {
            receiptRepository.findById(receiptId).orElseThrow(() -> new ReceiptServiceException("Receipt not found!"));
            receiptForSave.setId(receiptId);
        }

        return receiptRepository.save(receiptForSave);
    }

    @Override
    public void delete(long receiptId) {
        receiptRepository.deleteById(receiptId);
    }

    @NotNull
    private Author findAuthorOrSaveAndReturn(@NotNull Author authorFromReceiptDto) {
        var authorName = authorFromReceiptDto.getName();
        var authorLastName = authorFromReceiptDto.getLastName();
        var authorCountry = authorFromReceiptDto.getCountry();

        if ((authorName == null || authorName.isEmpty()) || (authorLastName == null || authorLastName.isEmpty())
                || authorCountry == null) {
            throw new ReceiptServiceException("Receipt authors name/lastName/country can't be null or empty!");
        }

        var authorCountryFromDB = countryRepository.findByName(authorCountry.getName())
                .orElseThrow(() -> new ReceiptServiceException("Cant find authors country: " + authorCountry.getName()));

        return authorRepository.findByNameAndLastNameAndCountry(authorName, authorLastName, authorCountryFromDB)
                .orElse(authorRepository.save(authorFromReceiptDto));
    }
}
