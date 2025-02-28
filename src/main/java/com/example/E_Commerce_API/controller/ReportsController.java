package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dto.request.ReportsRequest;
import com.example.E_Commerce_API.dto.request.ReportsStatusRequest;
import com.example.E_Commerce_API.dto.response.ReportsPageResponse;
import com.example.E_Commerce_API.dto.response.ReportsStatusResponse;
import com.example.E_Commerce_API.security.AuthenticationHelperService;
import com.example.E_Commerce_API.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportsController {
    private final ReportsService reportsService;
    private final AuthenticationHelperService authenticationHelperService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public void newReport(@RequestBody ReportsRequest reportsRequest) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        reportsService.newReport(currentUserEmail, reportsRequest);
    }

    @GetMapping("/show-all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ReportsPageResponse> getAllReports(@RequestParam(value = "page") int page,
                                                             @RequestParam(value = "count") int count) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ReportsPageResponse listReports = reportsService.getAllReports(currentUserEmail, page, count);
        return ResponseEntity.ok(listReports);
    }

    @PatchMapping("/set-status")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ReportsStatusResponse> setStatusReport(@RequestBody ReportsStatusRequest reportsStatusRequest, Long id) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        ReportsStatusResponse reportsStatusResponse = reportsService.setStatusReport(currentUserEmail, id, reportsStatusRequest);
        return ResponseEntity.ok(reportsStatusResponse);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteReport(@RequestBody Long id) {
        String currentUserEmail = authenticationHelperService.getCurrentUserEmail();
        reportsService.deleteReport(currentUserEmail, id);
    }
}
