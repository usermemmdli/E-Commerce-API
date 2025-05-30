package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.request.ReviewsRequest;
import com.example.E_Commerce_API.dto.response.pagination.ReviewsPageResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.ReviewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping("/show")
    @PreAuthorize("hasAnyRole('ADMIN','USER','SELLER')")
    public ResponseEntity<ReviewsPageResponse> showReviewsByProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "count", defaultValue = "5") int count,
                                                                     @RequestParam String productId) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ReviewsPageResponse reviewsPageResponse = reviewsService.showReviewsByProducts(currentUserEmail, page, count, productId);
        return ResponseEntity.ok(reviewsPageResponse);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public void addReview(@RequestBody @Valid ReviewsRequest reviewsRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        reviewsService.addReview(currentUserEmail, reviewsRequest);
    }
}
