package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Reviews;
import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.ProductsRepository;
import com.example.E_Commerce_API.dao.repository.ReviewsRepository;
import com.example.E_Commerce_API.dto.request.ReviewsRequest;
import com.example.E_Commerce_API.dto.response.ReviewsPageResponse;
import com.example.E_Commerce_API.dto.response.ReviewsResponse;
import com.example.E_Commerce_API.mapper.ReviewsMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;
    private final ProductsRepository productsRepository;
    private final ReviewsMapper reviewsMapper;
    private final AuthenticationHelperService authenticationHelperService;

    public ReviewsPageResponse showReviews(String currentUserEmail, int page, int count) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Page<Reviews> allReviews = reviewsRepository.findAll(PageRequest.of(page, count));
        List<ReviewsResponse> reviewsList = new CopyOnWriteArrayList<>(allReviews.getContent().stream().map(reviewsMapper::toReviewsResponse).toList());
        return new ReviewsPageResponse(reviewsList, allReviews.getTotalElements(), allReviews.getTotalPages(), allReviews.hasNext());
    }

    public void addReview(String currentUserEmail, ReviewsRequest reviewsRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Reviews reviews = new Reviews();
        reviews.setUsers(users);
        if (reviewsRequest.getProductId() != null && productsRepository.findById(reviewsRequest.getProductId()).isPresent()) {
            reviews.setProducts(productsRepository.findById(reviewsRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found")));
        }
        reviews.setDescription(reviewsRequest.getDescription());
        reviews.setRating(reviewsRequest.getRating());
        reviews.setCreatedAt(Timestamp.from(Instant.now()));
        reviewsRepository.save(reviews);
    }
}
