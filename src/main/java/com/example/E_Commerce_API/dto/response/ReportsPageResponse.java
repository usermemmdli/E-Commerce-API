package com.example.E_Commerce_API.dto.response;

import java.util.List;

public record ReportsPageResponse(
        List<ReportsResponse> reportsResponse,
        long totalElements,
        int totalPages,
        boolean hasNextPages) {
}
