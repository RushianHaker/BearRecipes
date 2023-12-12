package com.service.bearrecipes.dao;

import com.service.bearrecipes.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByStockAddressCity(String stockAddressCity);
}
