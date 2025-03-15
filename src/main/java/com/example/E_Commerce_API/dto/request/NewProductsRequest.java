package com.example.E_Commerce_API.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewProductsRequest {
    @NotNull
    private String name;
    @NotNull
    private String price;
    @NotNull
    private String city;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    private Boolean delivery;
    private Boolean status;
    @NotNull
    private String description;
    @NotNull
    private String categoriesName;
    @NotNull
    private String imageUrl;
}
