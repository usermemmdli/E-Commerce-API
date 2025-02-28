package com.example.E_Commerce_API.dto.response;

import java.util.List;

public record ProductsPageResponse(
        List<ProductsResponse> productsResponses,
        long totalElements,
        int totalPages,
        boolean hasNextPages
) {
}
