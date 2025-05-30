package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEditResponse {
    private String name;
    private String phoneNumber;
    private String email;
}
