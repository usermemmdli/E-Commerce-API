package com.example.E_Commerce_API.controller;

import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.UsersRepository;
import com.example.E_Commerce_API.dto.request.LoginRequest;
import com.example.E_Commerce_API.dto.request.SignUpRequest;
import com.example.E_Commerce_API.dto.response.JwtResponse;
import com.example.E_Commerce_API.service.AuthService;
import com.example.E_Commerce_API.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UsersRepository usersRepository;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        authService.signUpUser(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        if (refreshToken == null || !jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }
        String email = jwtService.extractUsername(refreshToken);

        if (email == null) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String newAccessToken = jwtService.createAccessToken(users);

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", newAccessToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
