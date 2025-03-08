package com.example.E_Commerce_API.dto.response.pagination;

import com.example.E_Commerce_API.dto.response.ReportsResponse;

import java.util.List;

public record ReportsPageResponse(
        List<ReportsResponse> reportsResponse,
        long totalElements,
        int totalPages,
        boolean hasNextPages) {
}
