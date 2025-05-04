package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.*;
import com.example.E_Commerce_API.dao.repository.*;
import com.example.E_Commerce_API.dto.request.NewProductsRequest;
import com.example.E_Commerce_API.dto.request.ProductsEditRequest;
import com.example.E_Commerce_API.dto.response.ProductsEditResponse;
import com.example.E_Commerce_API.dto.response.pagination.ProductsPageResponse;
import com.example.E_Commerce_API.dto.response.ProductsResponse;
import com.example.E_Commerce_API.exception.CategoryNotFoundException;
import com.example.E_Commerce_API.exception.ProductsNotFoundException;
import com.example.E_Commerce_API.exception.RoleNotFoundException;
import com.example.E_Commerce_API.mapper.ProductsMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductsMapper productsMapper;
    private final CategoriesRepository categoriesRepository;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final BookmarksRepository bookmarksRepository;
    private final AuthenticationHelperService authenticationHelperService;

    public ProductsPageResponse getAllProducts(String currentUserEmail, int page, int count) {
        authenticationHelperService.getAuthenticatedUser(currentUserEmail);

        Pageable pageable = PageRequest.of(page, count);

        Page<Products> allProducts = productsRepository.findAll(pageable);
        List<ProductsResponse> productsList = allProducts.getContent().stream()
                .map(productsMapper::toProductsResponse)
                .collect(Collectors.toList());

        return new ProductsPageResponse(
                productsList,
                allProducts.getTotalElements(),
                allProducts.getTotalPages(),
                allProducts.hasNext()
        );
    }

    public ProductsPageResponse getAllProductsByCategory(String currentUserEmail, String categoriesName, int page, int count) {
        authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Page<Products> allProductsByCategory = productsRepository.findByCategoriesName(categoriesName, PageRequest.of(page, count));
        List<ProductsResponse> productsList = allProductsByCategory.stream()
                .map(productsMapper::toProductsResponse)
                .collect(Collectors.toList());

        return new ProductsPageResponse(
                productsList,
                allProductsByCategory.getTotalElements(),
                allProductsByCategory.getTotalPages(),
                allProductsByCategory.hasNext()
        );
    }

    public void newProduct(String currentUserEmail, NewProductsRequest newProductsRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Roles roles = rolesRepository.findByName("SELLER")
                .orElseThrow(() -> new RoleNotFoundException("No roles found"));
        users.setRoles(roles);
        Categories categories = categoriesRepository.findByName(newProductsRequest.getCategoriesName())
                .orElseThrow(() -> new CategoryNotFoundException("No category found"));

        Products products = new Products();
        products.setName(newProductsRequest.getName());
        products.setPrice(newProductsRequest.getPrice());
        products.setCity(newProductsRequest.getCity());
        products.setBrand(newProductsRequest.getBrand());
        products.setModel(newProductsRequest.getModel());
        products.setDelivery(newProductsRequest.getDelivery());
        products.setStatus(newProductsRequest.getStatus());
        products.setDescription(newProductsRequest.getDescription());
        products.setCategoriesName(categories.getName());
        products.setUsersId(users.getId());
        products.setCreatedAt(Timestamp.from(Instant.now()));
        products.setImageUrl(newProductsRequest.getImageUrl());
        usersRepository.save(users);
        productsRepository.save(products);
    }

    public void addProductsToBookmarks(String currentUserEmail, String id) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Bookmarks bookmarks = new Bookmarks();
        bookmarks.setUsersId(users.getId());
        bookmarks.setProductsId(id);
        bookmarks.setCreatedAt(Timestamp.from(Instant.now()));
        bookmarksRepository.save(bookmarks);
    }

    public ProductsEditResponse editProduct(String currentUserEmail, ProductsEditRequest productsEditRequest) {
        authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        categoriesRepository.findByName(productsEditRequest.getCategoriesName())
                .orElseThrow(() -> new CategoryNotFoundException("No Category found"));

        Products products = productsRepository.findById(productsEditRequest.getId())
                .orElseThrow(() -> new ProductsNotFoundException("Product not found"));
        products.setName(productsEditRequest.getName());
        products.setPrice(productsEditRequest.getPrice());
        products.setCity(productsEditRequest.getCity());
        products.setBrand(productsEditRequest.getBrand());
        products.setModel(productsEditRequest.getModel());
        products.setDelivery(productsEditRequest.getDelivery());
        products.setStatus(productsEditRequest.getStatus());
        products.setDescription(productsEditRequest.getDescription());
        products.setCategoriesName(productsEditRequest.getCategoriesName());
        products.setUpdatedAt(Timestamp.from(Instant.now()));
        products.setImageUrl(productsEditRequest.getImageUrl());
        return productsMapper.toProductsEditResponse(products);
    }

    public void deleteProduct(String currentUserEmail, String id) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (productsRepository.existsByUsersId(users.getId()) && productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        } else {
            throw new ProductsNotFoundException("Product not found");
        }
    }
}
