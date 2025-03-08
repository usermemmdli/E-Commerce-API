package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.request.ReviewsRequest;
import com.example.E_Commerce_API.dto.response.pagination.ReviewsPageResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.ReviewsService;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER','SELLER')")
    public ResponseEntity<ReviewsPageResponse> showReviews(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "count", defaultValue = "5") int count) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ReviewsPageResponse reviewsPageResponse = reviewsService.showReviews(currentUserEmail, page, count);
        return ResponseEntity.ok(reviewsPageResponse);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public void addReview(@RequestBody ReviewsRequest reviewsRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        reviewsService.addReview(currentUserEmail, reviewsRequest);
    }
}
