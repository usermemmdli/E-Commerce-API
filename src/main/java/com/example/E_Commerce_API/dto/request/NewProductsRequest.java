package com.example.E_Commerce_API.dto.request;

import lombok.Data;

@Data
public class NewProductsRequest {
    private String name;
    private String price;
    private String city;
    private String brand;
    private String model;
    private Boolean delivery;
    private Boolean status;
    private String description;
    private String categoriesName;
    private String imageUrl;
}
