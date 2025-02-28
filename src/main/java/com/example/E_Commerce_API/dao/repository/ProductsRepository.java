package com.example.E_Commerce_API.dao.repository;

import com.example.E_Commerce_API.dao.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findByUsersId(Long users_id);

    Page<Products> findByCategories_Name(@Param("name") String categories_name, Pageable pageable);
}
