package com.example.E_Commerce_API.exception;

public class BookmarksNotFoundException extends RuntimeException {
    public BookmarksNotFoundException(String message) {
        super(message);
    }
}
