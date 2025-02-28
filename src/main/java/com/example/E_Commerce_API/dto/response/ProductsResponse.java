package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ProductsResponse {
    private Long id;
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
