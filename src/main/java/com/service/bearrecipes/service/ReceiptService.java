package com.service.bearrecipes.service;

import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.Receipt;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ReceiptService {
    Receipt saveOrUpdate(@NotNull ReceiptDTO receiptDTO);

    ReceiptDTO findById(long receiptId);

    ReceiptDTO findByName(@NotNull String receiptIName);

    List<ReceiptDTO> findAll();

    void delete(long receiptId);
}
