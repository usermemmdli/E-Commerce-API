package com.example.E_Commerce_API.dto.response.pagination;

import com.example.E_Commerce_API.dto.response.ProductsResponse;

import java.util.List;

public record BookmarksPageResponse(
        List<ProductsResponse> productsResponse,
        long totalElements,
        int totalPages,
        boolean hasNextPages) {
}
