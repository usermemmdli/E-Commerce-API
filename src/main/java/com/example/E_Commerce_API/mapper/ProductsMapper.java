package com.example.E_Commerce_API.mapper;

import com.example.E_Commerce_API.dao.entity.Products;
import com.example.E_Commerce_API.dto.response.ProductsEditResponse;
import com.example.E_Commerce_API.dto.response.ProductsResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@Mapper(componentModel = "spring")
public class ProductsMapper {
    public ProductsEditResponse toProductsEditResponse(Products products) {
        return ProductsEditResponse.builder()
                .name(products.getName())
                .price(products.getPrice())
                .city(products.getCity())
                .brand(products.getBrand())
                .model(products.getModel())
                .delivery(products.getDelivery())
                .status(products.getStatus())
                .description(products.getDescription())
                .categoriesName(products.getCategories().getName())
                .updatedAt(Timestamp.from(Instant.now()))
                .imageUrl(products.getImageUrl())
                .build();
    }

    public ProductsResponse toProductsResponse(Products products) {
        return ProductsResponse.builder()
                .id(products.getId())
                .name(products.getName())
                .price(products.getPrice())
                .city(products.getCity())
                .brand(products.getBrand())
                .model(products.getModel())
                .delivery(products.getDelivery())
                .status(products.getStatus())
                .description(products.getDescription())
                .categoriesName(products.getCategories().getName())
                .updatedAt(Timestamp.from(Instant.now()))
                .imageUrl(products.getImageUrl())
                .build();
    }
}
