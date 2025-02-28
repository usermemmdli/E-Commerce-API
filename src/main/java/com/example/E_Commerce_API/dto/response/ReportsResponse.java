package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ReportsResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private String description;
    private Boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
