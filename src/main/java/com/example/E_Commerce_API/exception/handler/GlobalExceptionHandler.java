package com.example.E_Commerce_API.exception.handler;

import com.example.E_Commerce_API.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRoleNotFoundException(RoleNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Default role not found");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyIsTakenException.class)
    public ResponseEntity<?> handleEmailAlreadyIsTakenException(EmailAlreadyIsTakenException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Email is already taken");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<?> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Email or password is invalid");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookmarksNotFoundException.class)
    public ResponseEntity<?> handleBookmarksNotFoundException(BookmarksNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Bookmark not found");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Category not found");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<?> handleProductsNotFoundException(ProductsNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Product not found");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReportsNotFoundException.class)
    public ResponseEntity<?> handleReportsNotFoundException(ReportsNotFoundException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Report not found");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Password does not match");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUserEditRequestException.class)
    public ResponseEntity<?> handleInvalidUserEditRequestException(InvalidUserEditRequestException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Invalid user edit");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
