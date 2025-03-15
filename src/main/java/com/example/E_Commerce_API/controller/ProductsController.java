package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.request.NewProductsRequest;
import com.example.E_Commerce_API.dto.request.ProductsEditRequest;
import com.example.E_Commerce_API.dto.response.ProductsEditResponse;
import com.example.E_Commerce_API.dto.response.pagination.ProductsPageResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.ProductsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductsService productsService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SELLER')")
    public ResponseEntity<ProductsPageResponse> getAllProducts(@RequestParam(value = "page") int page,
                                                               @RequestParam(value = "count") int count) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ProductsPageResponse productsPageResponses = productsService.getAllProducts(currentUserEmail, page, count);
        return ResponseEntity.ok(productsPageResponses);
    }

    @GetMapping("/by-categories")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SELLER')")
    public ResponseEntity<ProductsPageResponse> getAllProductsByCategory(@RequestParam(value = "name") String categoriesName,
                                                                         @RequestParam(value = "page") int page,
                                                                         @RequestParam(value = "count") int count) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ProductsPageResponse productsPageResponse = productsService.getAllProductsByCategory(currentUserEmail, categoriesName, page, count);
        return ResponseEntity.ok(productsPageResponse);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public void newProduct(@RequestBody @Valid NewProductsRequest newProductsRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        productsService.newProduct(currentUserEmail, newProductsRequest);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public void addProductsToBookmarks(@PathVariable String id) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        productsService.addProductsToBookmarks(currentUserEmail, id);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<ProductsEditResponse> editProduct(@RequestBody @Valid ProductsEditRequest productsEditRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ProductsEditResponse editProduct = productsService.editProduct(currentUserEmail, productsEditRequest);
        return ResponseEntity.ok(editProduct);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('SELLER')")
    public void deleteProduct(@PathVariable String id) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        productsService.deleteProduct(currentUserEmail, id);
    }
}
