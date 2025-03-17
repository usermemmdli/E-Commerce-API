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

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
            RoleNotFoundException.class, HttpStatus.NOT_FOUND,
            EmailAlreadyIsTakenException.class, HttpStatus.BAD_REQUEST,
            InvalidEmailOrPasswordException.class, HttpStatus.BAD_REQUEST,
            BookmarksNotFoundException.class, HttpStatus.NOT_FOUND,
            CategoryNotFoundException.class, HttpStatus.NOT_FOUND,
            ProductsNotFoundException.class, HttpStatus.NOT_FOUND,
            ReportsNotFoundException.class, HttpStatus.NOT_FOUND,
            InvalidPasswordException.class, HttpStatus.BAD_REQUEST,
            InvalidUserEditRequestException.class, HttpStatus.BAD_REQUEST,
            InvalidValueException.class, HttpStatus.BAD_REQUEST
    );

    @ExceptionHandler({
            RoleNotFoundException.class,
            EmailAlreadyIsTakenException.class,
            InvalidEmailOrPasswordException.class,
            BookmarksNotFoundException.class,
            CategoryNotFoundException.class,
            ProductsNotFoundException.class,
            ReportsNotFoundException.class,
            InvalidPasswordException.class,
            InvalidUserEditRequestException.class,
            InvalidValueException.class
    })
    public ResponseEntity<Map<String, Object>> handleCustomExceptions(Exception e) {
        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, status);
    }
}
