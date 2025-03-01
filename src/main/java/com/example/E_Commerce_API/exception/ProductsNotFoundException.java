package com.example.E_Commerce_API.exception;

public class ProductsNotFoundException extends RuntimeException {
    public ProductsNotFoundException(String message) {
        super(message);
    }
}
