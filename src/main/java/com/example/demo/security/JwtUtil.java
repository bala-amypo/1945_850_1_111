package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Use the same secret you had earlier (must be long enough for HMAC-SHA algorithms)
    private final String secretString = "your-very-long-secret-key-change-me-1234567890";
    private final SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes());

    // Example: 24 hours validity
    private final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000L;

    // This signature matches your error message: (String, String, Long, String)
    public String generateToken(String username, String role, Long userId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // Central place to parse token with the new, non-deprecated API
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Used by your filter to get email from token
    public String extractEmail(String token) {
        Claims claims = parseToken(token);
        return claims.get("email", String.class);
    }

    public String extractUsername(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    private Date extractExpiration(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    // Used by your filter/service to validate the token against the email
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);
        return extractedEmail != null
                && extractedEmail.equals(email)
                && !isTokenExpired(token);
    }
}
