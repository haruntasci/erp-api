package com.example.erp.exception;

public class OrderAlreadyApprovedException extends Exception{
    public OrderAlreadyApprovedException(String message) {
        super(message);
    }
}
