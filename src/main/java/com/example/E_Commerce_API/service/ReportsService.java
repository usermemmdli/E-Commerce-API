package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Products;
import com.example.E_Commerce_API.dao.entity.Reports;
import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.ProductsRepository;
import com.example.E_Commerce_API.dao.repository.ReportsRepository;
import com.example.E_Commerce_API.dto.request.ReportsRequest;
import com.example.E_Commerce_API.dto.request.ReportsStatusRequest;
import com.example.E_Commerce_API.dto.response.ReportsPageResponse;
import com.example.E_Commerce_API.dto.response.ReportsResponse;
import com.example.E_Commerce_API.dto.response.ReportsStatusResponse;
import com.example.E_Commerce_API.exception.ProductsNotFoundException;
import com.example.E_Commerce_API.exception.ReportsNotFoundException;
import com.example.E_Commerce_API.mapper.ReportsMapper;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportsService {
    private final ReportsRepository reportsRepository;
    private final ReportsMapper reportsMapper;
    private final ProductsRepository productsRepository;
    private final AuthenticationHelperService authenticationHelperService;

    public void newReport(String currentUserEmail, ReportsRequest reportsRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Reports reports = new Reports();
        reports.setUsers(users);
        if (reportsRequest.getProductId() != null) {
            Products product = productsRepository.findById(reportsRequest.getProductId())
                    .orElseThrow(() -> new ProductsNotFoundException("Product not found"));
            reports.setProducts(product);
        }
        reports.setStatus(false);
        reports.setDescription(reportsRequest.getDescription());
        reportsRepository.save(reports);
    }

    public ReportsPageResponse getAllReports(String currentUserEmail, int page, int count) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Page<Reports> allReports = reportsRepository.findAll(PageRequest.of(page, count));
        List<ReportsResponse> reportsList = new CopyOnWriteArrayList<>(
                allReports.getContent().stream()
                        .map(reportsMapper::toReportsResponse)
                        .toList());

        return new ReportsPageResponse(
                reportsList,
                allReports.getTotalElements(),
                allReports.getTotalPages(),
                allReports.hasNext()
        );
    }

    public ReportsStatusResponse setStatusReport(String currentUserEmail, Long id, ReportsStatusRequest reportsStatusRequest) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        Reports reports = reportsRepository.findById(id)
                .orElseThrow(() -> new ReportsNotFoundException("Report not found"));
        reports.setStatus(reportsStatusRequest.getStatus());
        reports.setUpdatedAt(Timestamp.from(Instant.now()));
        reportsRepository.save(reports);
        return reportsMapper.toReportsStatusResponse(reports);
    }

    public void deleteReport(String currentUserEmail, Long id) {
        Users users = authenticationHelperService.getAuthenticatedUser(currentUserEmail);
        if (reportsRepository.findById(id).isPresent()) {
            reportsRepository.deleteById(id);
        } else {
            throw new ProductsNotFoundException("Product not found");
        }
    }
}
