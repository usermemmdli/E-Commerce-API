package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReportsResponse {
    private String id;
    private Long userId;
    private String productId;
    private String description;
    private Boolean status;
    private Date createdAt;
    private Date updatedAt;
}
