package com.service.bearrecipes.exception;


public class CountryServiceException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public CountryServiceException(String s) {
        super(s);
    }
}
