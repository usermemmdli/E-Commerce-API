package com.example.E_Commerce_API.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportsRequest {
    @NotNull
    private String productId;
    @NotNull
    private String description;
}
