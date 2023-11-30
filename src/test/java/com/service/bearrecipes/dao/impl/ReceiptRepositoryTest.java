package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.dao.ReceiptRepository;
import com.service.bearrecipes.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ReceiptRepositoryTest {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findById() {
        var receipt = receiptRepository.findById(1);

        assertTrue(receipt.isPresent());

        var presentReceipt = receipt.get();
        assertEquals(1, presentReceipt.getId());
        assertEquals("Test Receipt", presentReceipt.getName());
        assertEquals("Test Receipt", presentReceipt.getPlaintText());

        assertEquals(1, presentReceipt.getAuthor().getId());
        assertEquals("Test Author", presentReceipt.getAuthor().getName());
        assertEquals("Test Author", presentReceipt.getAuthor().getLastName());

        assertEquals(9999, presentReceipt.getCountry().getId());
        assertEquals("Test Country", presentReceipt.getCountry().getName());

        assertEquals(1, presentReceipt.getIngredients().get(0).getId());
        assertEquals("Test Ingredient", presentReceipt.getIngredients().get(0).getIngredientName());
    }

    @Test
    void findAll() {
        var receipts = receiptRepository.findAll();

        assertEquals(1, receipts.size());

        var presentReceipt = receipts.get(0);
        assertEquals(1, presentReceipt.getId());
        assertEquals("Test Receipt", presentReceipt.getName());

        assertEquals(1, presentReceipt.getAuthor().getId());
        assertEquals("Test Author", presentReceipt.getAuthor().getName());

        assertEquals(9999, presentReceipt.getCountry().getId());
        assertEquals("Test Country", presentReceipt.getCountry().getName());

        assertEquals(1, presentReceipt.getIngredients().get(0).getId());
        assertEquals("Test Ingredient", presentReceipt.getIngredients().get(0).getIngredientName());
    }

    @Test
    void save() {
        var receipt = new Receipt("testReceipt", new byte[0], "testReceipt", 111L,
                new Author(1L, "testAuthor", "testAuthor", new Country(9999L, "testCountry")),
                new Country(9999L, "testCountry"), List.of(new Ingredient(1L, "testIngredient",
                BigDecimal.valueOf(111), BigDecimal.valueOf(111), new Receipt(1L))),
                List.of(new StepInfo(1L, "testStepInfo", new byte[0], new Receipt(1L))));

        var saveReceipt = receiptRepository.save(receipt);
        var receiptById = em.find(Receipt.class, 2);

        assertEquals(saveReceipt.getId(), receiptById.getId());
        assertEquals(saveReceipt.getName(), receiptById.getName());

        assertEquals(saveReceipt.getAuthor().getName(), receiptById.getAuthor().getName());
        assertEquals(saveReceipt.getAuthor().getLastName(), receiptById.getAuthor().getLastName());

        assertEquals(saveReceipt.getCountry().getName(), receiptById.getCountry().getName());

        assertEquals(saveReceipt.getIngredients().get(0).getIngredientName(), receiptById.getIngredients().get(0).getIngredientName());
    }

    @Test
    @Rollback
    void deleteById() {
        var receipt = em.find(Receipt.class, 1);
        assertEquals("Test Receipt", receipt.getName());
        assertEquals("Test Receipt", receipt.getPlaintText());

        receiptRepository.deleteById(1L);

        assertNull(em.find(Receipt.class, 1));
    }
}
