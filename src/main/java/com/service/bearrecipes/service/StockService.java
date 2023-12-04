package com.service.bearrecipes.service;


import com.service.bearrecipes.model.Stock;

import java.util.List;

public interface StockService {
    Stock findByStockAddressCity(String stockAddressCity);

    List<Stock> findAll();
}
