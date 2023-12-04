package com.service.bearrecipes.exception;


public class OrderServiceException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public OrderServiceException(String s) {
        super(s);
    }
}
