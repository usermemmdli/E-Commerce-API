package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Categories;
import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.CategoriesRepository;
import com.example.E_Commerce_API.dto.request.CategoriesRequest;
import com.example.E_Commerce_API.dto.response.CategoriesResponse;
import com.example.E_Commerce_API.exception.CategoryNotFoundException;
import com.example.E_Commerce_API.mapper.CategoriesMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final AuthenticationHelperService authenticationHelperService;

    public List<CategoriesResponse> getAllCategories(String currentUserEmail) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        List<Categories> getAllCategories = categoriesRepository.findAll();
        return getAllCategories
                .stream()
                .map(CategoriesMapper::toCategoriesResponse)
                .collect(Collectors.toList());
    }


    public CategoriesResponse addCategories(String currentUserEmail, CategoriesRequest categoriesRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Categories categories = new Categories();
        if (categoriesRequest.getName() != null) {
            categories.setName(categoriesRequest.getName());
        }
        return CategoriesMapper.toCategoriesResponse(categoriesRepository.save(categories));
    }

    public void deleteCategories(String currentUserEmail, CategoriesRequest categoriesRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (categoriesRequest.getName() != null && categoriesRepository.existsByName(categoriesRequest.getName())) {
            categoriesRepository.deleteByName(categoriesRequest.getName());
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }
}
