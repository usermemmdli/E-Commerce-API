package com.example.E_Commerce_API.dto.response;

import java.util.List;

public record BookmarksPageResponse(
        List<ProductsResponse> productsResponse,
        long totalElements,
        int totalPages,
        boolean hasNextPages
) {
}
