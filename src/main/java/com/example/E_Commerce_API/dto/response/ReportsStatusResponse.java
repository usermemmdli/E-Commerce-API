package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ReportsStatusResponse {
    private Boolean status;
    private Timestamp updatedAt;
}
