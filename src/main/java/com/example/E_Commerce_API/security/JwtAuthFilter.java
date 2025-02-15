package com.example.E_Commerce_API.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final MyUsersDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        log.debug("Authorization Header: {}", authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            log.debug("Extracted Token: {}", token);

            try {
                // Token'dan kullanıcı adını çıkar
                String username = jwtService.extractUsername(token);
                log.debug("Extracted Username: {}", username);

                // SecurityContext'te henüz authentication yok mu kontrol et
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Kullanıcı detaylarını yükle
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    log.debug("User Authorities: {}", userDetails.getAuthorities());

                    // Token geçerli mi kontrol et
                    if (jwtService.isTokenValid(token, userDetails)) {
                        // Authentication nesnesi oluştur
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                        // Request detaylarını ekle
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // SecurityContext'e authentication'ı ayarla
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        log.debug("Authentication set in SecurityContext");
                    } else {
                        log.debug("Token validation failed");
                    }
                }
            } catch (Exception e) {
                log.error("JWT Authentication Error", e);
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}