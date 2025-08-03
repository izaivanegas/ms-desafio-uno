package com.example.demo.exeption;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Product not found with id: %d";

    /**
     * Constructs a ProductNotFoundException with a specific product ID.
     *
     * @param id the ID of the product that was not found
     */
    public ProductNotFoundException(Long id) {
     super(String.format(MESSAGE,id));
    }
}
