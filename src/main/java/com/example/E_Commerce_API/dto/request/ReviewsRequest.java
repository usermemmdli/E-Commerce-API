package com.example.E_Commerce_API.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewsRequest {
    @NotNull
    private String productId;
    @NotNull
    private Integer rating;
    @NotNull
    private String description;
}
