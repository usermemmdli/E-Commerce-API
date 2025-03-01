package com.example.E_Commerce_API.exception;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
