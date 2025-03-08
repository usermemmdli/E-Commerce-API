package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.dao.entity.Roles;
import com.example.E_Commerce_API.dao.entity.Users;
import com.example.E_Commerce_API.dao.repository.RolesRepository;
import com.example.E_Commerce_API.dao.repository.UsersRepository;
import com.example.E_Commerce_API.dto.request.LoginRequest;
import com.example.E_Commerce_API.dto.request.SignUpRequest;
import com.example.E_Commerce_API.dto.response.JwtResponse;
import com.example.E_Commerce_API.exception.EmailAlreadyIsTakenException;
import com.example.E_Commerce_API.exception.InvalidEmailOrPasswordException;
import com.example.E_Commerce_API.exception.RoleNotFoundException;
import com.example.E_Commerce_API.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void signUpUser(SignUpRequest signUpRequest) {
        if (usersRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyIsTakenException("Email is already taken");
        }
        Users users = new Users();

        Roles defaultRole = rolesRepository.findByName("USER")
                .orElseThrow(() -> new RoleNotFoundException("No roles found"));

        users.setRoles(defaultRole);
        users.setName(signUpRequest.getName());
        users.setPhoneNumber(signUpRequest.getPhoneNumber());
        users.setEmail(signUpRequest.getEmail());
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setCreatedAt(Timestamp.from(Instant.now()));
        usersRepository.save(users);
    }

    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users users = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidEmailOrPasswordException("Email or password is invalid"));
        String accessToken = jwtService.createAccessToken(users);
        String refreshToken = jwtService.createRefreshToken(users);

        return new JwtResponse(accessToken, refreshToken);
    }
}
