package com.service.bearrecipes.dao.impl;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.dao.ReceiptRepository;
import com.service.bearrecipes.model.Author;
import com.service.bearrecipes.model.Country;
import com.service.bearrecipes.model.Receipt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {DbTestcontainersConfig.class})
class ReceiptRepositoryTest {
    @Autowired
    private ReceiptRepository receiptRepository;

    @Test
    void findById() {
        var receipt = receiptRepository.findById(1);

        assertTrue(receipt.isPresent());

        var presentReceipt = receipt.get();
        assertEquals(1, presentReceipt.getId());
        assertEquals("Test Receipt", presentReceipt.getName());
        assertEquals("Test Receipt", presentReceipt.getPlaintText());

        assertEquals(1, presentReceipt.getAuthor().getId());
        assertEquals("TestAuthor", presentReceipt.getAuthor().getName());
        assertEquals("TestAuthor", presentReceipt.getAuthor().getLastName());

        assertEquals(9999, presentReceipt.getCountry().getId());
        assertEquals("Test Country", presentReceipt.getCountry().getName());
    }

    @Test
    void findAll() {
        var receipts = receiptRepository.findAll();

        assertEquals(2, receipts.size());

        var presentReceipt = receipts.get(1);
        assertEquals(1, presentReceipt.getId());
        assertEquals("Test Receipt", presentReceipt.getName());

        assertEquals(1, presentReceipt.getAuthor().getId());
        assertEquals("TestAuthor", presentReceipt.getAuthor().getName());

        assertEquals(9999, presentReceipt.getCountry().getId());
        assertEquals("Test Country", presentReceipt.getCountry().getName());
    }

    @Test
    void save() {
        var receipt = new Receipt("testReceipt", new byte[0], "testReceipt", 111L,
                new Author(1L, "TestAuthor", "TestAuthor"),
                new Country(9999L, "Test Country"));

        var saveReceipt = receiptRepository.save(receipt);
        var receiptById = receiptRepository.findById(3).orElseThrow();

        assertEquals(saveReceipt.getId(), receiptById.getId());
        assertEquals(saveReceipt.getName(), receiptById.getName());

        assertEquals(saveReceipt.getAuthor().getName(), receiptById.getAuthor().getName());
        assertEquals(saveReceipt.getAuthor().getLastName(), receiptById.getAuthor().getLastName());

        assertEquals(saveReceipt.getCountry().getName(), receiptById.getCountry().getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var receipt = receiptRepository.findById(2).orElseThrow();
        assertEquals("Test Receipt 2", receipt.getName());
        assertEquals("Test Receipt 2", receipt.getPlaintText());

        receiptRepository.deleteById(2L);

        assertFalse(receiptRepository.findById(2).isPresent());
    }
}
