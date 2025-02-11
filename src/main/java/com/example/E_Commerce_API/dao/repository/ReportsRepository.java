package com.example.E_Commerce_API.dao.repository;

import com.example.E_Commerce_API.dao.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
