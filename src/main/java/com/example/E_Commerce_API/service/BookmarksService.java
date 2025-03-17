package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Bookmarks;
import com.example.E_Commerce_API.dao.entity.Products;
import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.BookmarksRepository;
import com.example.E_Commerce_API.dao.repository.ProductsRepository;
import com.example.E_Commerce_API.dto.response.pagination.BookmarksPageResponse;
import com.example.E_Commerce_API.dto.response.ProductsResponse;
import com.example.E_Commerce_API.exception.BookmarksNotFoundException;
import com.example.E_Commerce_API.exception.ProductsNotFoundException;
import com.example.E_Commerce_API.mapper.ProductsMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarksService {
    private final BookmarksRepository bookmarksRepository;
    private final ProductsRepository productsRepository;
    private final ProductsMapper productsMapper;
    private final AuthenticationHelperService authenticationHelperService;

    public BookmarksPageResponse showAllMarkedProducts(String currentUserEmail, int page, int count) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Pageable pageable = PageRequest.of(page, count);
        Page<Bookmarks> bookmarks = bookmarksRepository.findByUsersId(users.getId(), pageable);

        List<ProductsResponse> productsList = bookmarks.getContent().stream()
                .map(bookmark -> {
                    Products product = productsRepository.findById(bookmark.getProductsId())
                            .orElseThrow(() -> new ProductsNotFoundException("Product not found"));
                    return productsMapper.toProductsResponse(product);
                })
                .collect(Collectors.toList());


        return new BookmarksPageResponse(
                productsList,
                bookmarks.getTotalElements(),
                bookmarks.getTotalPages(),
                bookmarks.hasNext()
        );
    }

    public void deleteProductsMark(String currentUserEmail, String productId) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (bookmarksRepository.existsByUsersId(users.getId())) {
            bookmarksRepository.deleteById(productId);
        } else {
            throw new BookmarksNotFoundException("Bookmark not found");
        }
    }
}
