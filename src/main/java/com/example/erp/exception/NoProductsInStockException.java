package com.example.erp.exception;

public class NoProductsInStockException extends Exception {
    public NoProductsInStockException(String message) {
        super(message);
    }
}
