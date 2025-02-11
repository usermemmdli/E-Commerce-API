package com.example.E_Commerce_API.security;

import com.example.E_Commerce_API.dao.entity.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtService {
    private static final String SECRET_KEY = "ZFRoUWVsb3pjMGQ0TWtNM1pFWXhWbkUwVEcxT2IwVTRWSGxLZDFKaFZXdExha0ZwYjNKRFFraFNXRTlqYm5kdlNRPT0";
    private static final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 1000;
    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000;

    public static String createAccessToken(Users users) {
        return Jwts.builder()
                .setSubject(users.getEmail())
                .claim("roles", users.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String createRefreshToken(Users users) {
        return Jwts.builder()
                .setSubject(users.getEmail())
                .claim("roles", users.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody().isEmpty();
        } catch (ExpiredJwtException e) {
            log.error("Token expired");
            throw e;
        } catch (JwtException e) {
            log.error("Invalid token", e);
            throw e;
        }
    }

    private static Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
