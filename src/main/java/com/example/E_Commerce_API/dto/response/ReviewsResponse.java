package com.example.E_Commerce_API.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReviewsResponse {
    private Integer rating;
    private String description;
    private Date createdAt;
}
