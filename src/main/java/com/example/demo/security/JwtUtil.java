package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

// other imports...

public class JwtUtil {

    private final String secretString = "your-very-long-secret-key-change-me-1234567890";
    private final SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes());

    public String generateToken(String username, String role, String email, String userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("email", email)
                .claim("userId", userId)
                .signWith(key)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // keep your other existing methods here...
}
