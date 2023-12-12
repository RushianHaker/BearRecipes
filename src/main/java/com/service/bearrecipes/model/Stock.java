package com.service.bearrecipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "stock_address_city", nullable = false)
    private String stockAddressCity;

    @Column(name = "stock_tel_number", nullable = false)
    private String stockTelNumber;

    public Stock(String stockAddressCity, String stockTelNumber) {
        this.stockAddressCity = stockAddressCity;
        this.stockTelNumber = stockTelNumber;
    }
}
