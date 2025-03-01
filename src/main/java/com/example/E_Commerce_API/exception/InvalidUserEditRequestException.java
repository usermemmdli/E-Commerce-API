package com.example.E_Commerce_API.exception;

public class InvalidUserEditRequestException extends RuntimeException {
    public InvalidUserEditRequestException(String message) {
        super(message);
    }
}
