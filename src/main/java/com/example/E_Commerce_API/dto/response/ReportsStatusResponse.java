package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReportsStatusResponse {
    private Boolean status;
    private Date updatedAt;
}
