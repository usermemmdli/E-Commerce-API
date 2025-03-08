package com.example.E_Commerce_API.dao.repository;

import com.example.E_Commerce_API.dao.entity.Reviews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends MongoRepository<Reviews, String> {
}
