package com.example.E_Commerce_API.dto.response;

import java.util.List;

public record ReviewsPageResponse(
        List<ReviewsResponse> reviewsResponse,
        long totalElements,
        int totalPages,
        boolean hasNextPages
) {
}
