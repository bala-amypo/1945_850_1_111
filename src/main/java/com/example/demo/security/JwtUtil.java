package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Must be >= 32 characters for HS256
    private static final String SECRET =
            "secretkey123secretkey123secretkey123";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(
            String username,
            String role,
            String email,
            String id) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("email", email)
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public void validate(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()                 // 🔥 THIS WAS MISSING
                .parseClaimsJws(token);
    }
}
