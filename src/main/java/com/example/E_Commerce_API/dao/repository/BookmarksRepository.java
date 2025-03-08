package com.example.E_Commerce_API.dao.repository;

import com.example.E_Commerce_API.dao.entity.Bookmarks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookmarksRepository extends MongoRepository<Bookmarks, String> {
    Page<Bookmarks> findByUsersId (Long usersId, Pageable pageable);

    void deleteById(String id);

    boolean existsByUsersId(Long id);
}
