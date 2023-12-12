package com.service.bearrecipes.exception;


public class StockServiceException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public StockServiceException(String s) {
        super(s);
    }
}
