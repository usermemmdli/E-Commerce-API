package com.example.E_Commerce_API.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportsStatusRequest {
    @NotNull
    private String id;
    @NotNull
    private Boolean status;
}
