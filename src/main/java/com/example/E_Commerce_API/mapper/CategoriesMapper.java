package com.example.E_Commerce_API.mapper;

import com.example.E_Commerce_API.dao.entity.Categories;
import com.example.E_Commerce_API.dto.response.CategoriesResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class CategoriesMapper {
    public static CategoriesResponse toCategoriesResponse(Categories categories) {
        return CategoriesResponse.builder()
                .name(categories.getName())
                .build();
    }
}
