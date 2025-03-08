package com.example.E_Commerce_API.mapper;

import com.example.E_Commerce_API.dao.entity.Reports;
import com.example.E_Commerce_API.dto.response.ReportsResponse;
import com.example.E_Commerce_API.dto.response.ReportsStatusResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class ReportsMapper {
    public ReportsStatusResponse toReportsStatusResponse(Reports reports) {
        return ReportsStatusResponse.builder()
                .status(reports.getStatus())
                .updatedAt(reports.getUpdatedAt())
                .build();
    }

    public ReportsResponse toReportsResponse(Reports reports) {
        return ReportsResponse.builder()
                .id(reports.getId())
                .userId(reports.getUsersId())
                .productId(reports.getProductsId())
                .description(reports.getDescription())
                .status(reports.getStatus())
                .createdAt(reports.getCreatedAt())
                .updatedAt(reports.getUpdatedAt())
                .build();
    }
}
