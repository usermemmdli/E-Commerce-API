package com.example.E_Commerce_API.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SignUpRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Timestamp createdAt;
}
