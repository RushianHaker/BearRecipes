package com.service.bearrecipes.controller.rest;

import com.service.bearrecipes.model.Stock;
import com.service.bearrecipes.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockRestController {
    private final StockService stockService;

    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping({"/api/stock/{stockAddressCity}"})
    public Stock infoPageStock(@PathVariable("stockAddressCity") String stockAddressCity) {
        return stockService.findByStockAddressCity(stockAddressCity);
    }
}
