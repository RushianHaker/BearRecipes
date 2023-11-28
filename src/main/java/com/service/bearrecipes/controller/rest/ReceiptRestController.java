package com.service.bearrecipes.controller.rest;

import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.Receipt;
import com.service.bearrecipes.service.ReceiptService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReceiptRestController {
    private final ReceiptService receiptService;

    public ReceiptRestController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/api/receipt")
    public List<ReceiptDTO> getAllReceipts() {
        return receiptService.findAll();
    }

    @GetMapping({"/api/receipt/{id}"})
    public ReceiptDTO infoPageReceipt(@PathVariable("id") long id) {
        return receiptService.findById(id);
    }

    @GetMapping({"/api/receipt/{name}"})
    public ReceiptDTO infoPageReceiptByName(@PathVariable("name") @NotNull String name) {
        return receiptService.findByName(name);
    }

    @PostMapping("/api/receipt")
    public Receipt addReceipt(@RequestBody @NotNull ReceiptDTO receiptDTO) {
        return receiptService.saveOrUpdate(receiptDTO);
    }

    @PutMapping({"/api/receipt/{id}"})
    public Receipt editReceipt(@PathVariable("id") long id, @RequestBody @NotNull ReceiptDTO receiptDTO) {
        receiptDTO.setId(id);
        return receiptService.saveOrUpdate(receiptDTO);
    }

    @DeleteMapping({"/api/receipt/{id}"})
    public void deleteReceiptById(@PathVariable("id") long id) {
        receiptService.delete(id);
    }
}
