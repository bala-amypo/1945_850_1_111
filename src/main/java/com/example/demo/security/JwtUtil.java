package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "secretkey123";

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
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public void validate(String token) {
        // OLD JJWT (0.9.x) — THIS IS THE KEY
        Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);
    }
}
