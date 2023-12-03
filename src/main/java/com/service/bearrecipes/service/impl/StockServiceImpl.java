package com.service.bearrecipes.service.impl;

import com.service.bearrecipes.dao.StockRepository;
import com.service.bearrecipes.exception.StockServiceException;
import com.service.bearrecipes.model.Stock;
import com.service.bearrecipes.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;


    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock findByStockAddressCity(String stockAddressCity) {
        return stockRepository.findByStockAddressCity(stockAddressCity)
                .orElseThrow(() -> new StockServiceException("Can't find stock in city: " + stockAddressCity));
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
