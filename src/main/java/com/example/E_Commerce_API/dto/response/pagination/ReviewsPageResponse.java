package com.example.E_Commerce_API.dto.response.pagination;

import com.example.E_Commerce_API.dto.response.ReviewsResponse;

import java.util.List;

public record ReviewsPageResponse(
        List<ReviewsResponse> reviewsResponse,
        long totalElements,
        int totalPages,
        boolean hasNextPages) {
}
