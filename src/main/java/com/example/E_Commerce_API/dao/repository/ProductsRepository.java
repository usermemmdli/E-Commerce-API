package com.example.E_Commerce_API.dao.repository;

import com.example.E_Commerce_API.dao.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String> {

    Page<Products> findByCategoriesName(@Param("name") String categories_name, Pageable pageable);

    boolean existsByUsersId(Long id);
}
