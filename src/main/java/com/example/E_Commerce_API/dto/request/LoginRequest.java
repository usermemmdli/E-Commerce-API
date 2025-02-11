package com.example.E_Commerce_API.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
