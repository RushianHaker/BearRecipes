package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.AuthorRepository;
import com.service.bearrecipes.dao.CountryRepository;
import com.service.bearrecipes.dao.ReceiptRepository;
import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.*;
import com.service.bearrecipes.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {ReceiptServiceImpl.class})
class ReceiptServiceImplTest {
    @MockBean
    private ReceiptRepository receiptRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private ReceiptService service;

    @Test
    void save() {
        var receiptDTO = new ReceiptDTO("testReceipt", new byte[0], "testReceipt", 111L,
                new Author(2L, "testAuthor", "testAuthor", new Country(2L, "testCountry")),
                new Country(2L, "testCountry"), List.of(new Ingredient(2L, "testIngredient",
                BigDecimal.valueOf(111), BigDecimal.valueOf(111), new Receipt(2L))),
                List.of(new StepInfo(2L, "testStepInfo", new byte[0], new Receipt(2L))));

        when(receiptRepository.save(receiptDTO.toDomainObject())).thenReturn(receiptDTO.toDomainObject());

        service.saveOrUpdate(receiptDTO);

        ArgumentCaptor<Receipt> captor = ArgumentCaptor.forClass(Receipt.class);
        verify(receiptRepository, times(1)).save(captor.capture());

        var actualOutput = captor.getAllValues().get(0);

        assertEquals(actualOutput.getName(), receiptDTO.getName());
        assertEquals(actualOutput.getPlaintText(), receiptDTO.getPlaintText());
    }
}
