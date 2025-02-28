package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Bookmarks;
import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.BookmarksRepository;
import com.example.E_Commerce_API.dto.response.BookmarksPageResponse;
import com.example.E_Commerce_API.dto.response.ProductsResponse;
import com.example.E_Commerce_API.mapper.ProductsMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class BookmarksService {
    private final BookmarksRepository bookmarksRepository;
    private final ProductsMapper productsMapper;
    private final AuthenticationHelperService authenticationHelperService;

    public BookmarksPageResponse showAllMarkedProducts(String currentUserEmail, int page, int count) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Pageable pageable = PageRequest.of(page, count);
        Page<Bookmarks> bookmarks = bookmarksRepository.findByUsers_id(users.getId(), pageable);

        List<ProductsResponse> productsList = new CopyOnWriteArrayList<>(
                bookmarks.getContent().stream()
                        .map(Bookmarks::getProducts)
                        .map(productsMapper::toProductsResponse)
                        .toList());

        return new BookmarksPageResponse(
                productsList,
                bookmarks.getTotalElements(),
                bookmarks.getTotalPages(),
                bookmarks.hasNext()
        );
    }

    public void deleteProductsMark(String currentUserEmail, Long productId) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (bookmarksRepository.existsById(productId) && bookmarksRepository.existsById(users.getId())) {
            bookmarksRepository.deleteById(productId);
        } else {
            throw new RuntimeException("Bookmark not found");
        }
    }
}
