package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ReviewsResponse {
    private Integer rating;
    private String description;
    private Timestamp createdAt;
}
