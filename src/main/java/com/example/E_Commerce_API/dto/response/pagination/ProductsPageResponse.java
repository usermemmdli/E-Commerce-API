package com.example.E_Commerce_API.dto.response.pagination;

import com.example.E_Commerce_API.dto.response.ProductsResponse;

import java.util.List;

public record ProductsPageResponse(
        List<ProductsResponse> productsResponses,
        long totalElements,
        int totalPages,
        boolean hasNextPages) {
}
