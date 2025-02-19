package com.example.E_Commerce_API.dao.repository;

import com.example.E_Commerce_API.dao.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    boolean existsByName(String name);

    void deleteByName(String name);
}
