package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.request.CategoriesRequest;
import com.example.E_Commerce_API.dto.response.CategoriesResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping("/show-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategoriesResponse>> getAllCategories() {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        List<CategoriesResponse> categoriesResponses = categoriesService.getAllCategories(currentUserEmail);
        return ResponseEntity.ok(categoriesResponses);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriesResponse> addCategories(@RequestBody CategoriesRequest categoriesRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        CategoriesResponse addedCategories = categoriesService.addCategories(currentUserEmail, categoriesRequest);
        return ResponseEntity.ok(addedCategories);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategories(@RequestBody CategoriesRequest categoriesRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        categoriesService.deleteCategories(currentUserEmail, categoriesRequest);
    }
}
