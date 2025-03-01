package com.example.E_Commerce_API.exception;

public class EmailAlreadyIsTakenException extends RuntimeException {
    public EmailAlreadyIsTakenException(String message) {
        super(message);
    }
}
