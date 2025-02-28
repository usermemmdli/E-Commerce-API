package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class ProductsEditResponse {
    private String name;
    private String price;
    private String city;
    private String brand;
    private String model;
    private Boolean delivery;
    private Boolean status;
    private String description;
    private String categoriesName;
    private Timestamp updatedAt;
    private String imageUrl;
}
