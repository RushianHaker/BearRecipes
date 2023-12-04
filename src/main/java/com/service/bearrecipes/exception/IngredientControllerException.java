package com.service.bearrecipes.exception;


public class IngredientControllerException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public IngredientControllerException(String s) {
        super(s);
    }
}
