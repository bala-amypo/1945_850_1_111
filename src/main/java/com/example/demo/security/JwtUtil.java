package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 86400000; // 24 hours
    
    public String generateToken(String username, String role, String email, String studentId) {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .claim("email", email)
            .claim("studentId", studentId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact();
    }
    
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    
    public boolean validate(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}