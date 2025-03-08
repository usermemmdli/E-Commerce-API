package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductsResponse {
    private String id;
    private String name;
    private String price;
    private String city;
    private String brand;
    private String model;
    private Boolean delivery;
    private Boolean status;
    private String description;
    private String categoriesName;
    private Date createdAt;
    private Date updatedAt;
    private String imageUrl;
}
