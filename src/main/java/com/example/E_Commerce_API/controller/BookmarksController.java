package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.response.pagination.BookmarksPageResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.BookmarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarksController {
    private final BookmarksService bookmarksService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping("/show-all")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<BookmarksPageResponse> showAllMarkedProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "count", defaultValue = "5") int count) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        BookmarksPageResponse bookmarksPageResponse = bookmarksService.showAllMarkedProducts(currentUserEmail, page, count);
        return ResponseEntity.ok(bookmarksPageResponse);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public void deleteProductsMark(@PathVariable String productId) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        bookmarksService.deleteProductsMark(currentUserEmail, productId);
    }
}
