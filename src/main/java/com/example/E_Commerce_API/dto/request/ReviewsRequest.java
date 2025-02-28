package com.example.E_Commerce_API.dto.request;

import lombok.Data;


@Data
public class ReviewsRequest {
    private Long productId;
    private Integer rating;
    private String description;
}
