package com.service.bearrecipes.exception;


public class ReceiptServiceException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public ReceiptServiceException(String s) {
        super(s);
    }
}
