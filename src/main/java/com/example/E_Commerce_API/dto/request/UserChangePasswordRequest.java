package com.example.E_Commerce_API.dto.request;

import lombok.Data;

@Data
public class UserChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
