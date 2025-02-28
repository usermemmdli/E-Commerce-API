package com.example.E_Commerce_API.mapper;

import com.example.E_Commerce_API.dao.entity.Reviews;
import com.example.E_Commerce_API.dto.response.ReviewsResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class ReviewsMapper {
    public ReviewsResponse toReviewsResponse(Reviews reviews) {
        return ReviewsResponse.builder()
                .rating(reviews.getRating())
                .description(reviews.getDescription())
                .createdAt(reviews.getCreatedAt())
                .build();
    }
}
