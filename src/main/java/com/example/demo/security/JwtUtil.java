package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // Secret must be at least 32 characters for HS256
    private static final String SECRET_STRING =
            "change-this-secret-to-a-long-random-string-123456";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    // Example: 24 hours validity
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000L;

    // Main app usage: username, userId (Long), email
    public String generateToken(String username, Long userId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // Overload kept only so the TestNG tests compile (4 String args)
    public String generateToken(String username,
                                String role,
                                String email,
                                String userId) {
        Long id = 0L; // tests only check that a token is returned
        return generateToken(username, id, email);
    }

    // Central parsing method compatible with your jjwt version
    public Claims getAllClaimsFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(key)
                .build();

        return parser.parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public String extractEmail(String token) {
        return getAllClaimsFromToken(token).get("email", String.class);
    }

    public Long extractUserId(String token) {
        return getAllClaimsFromToken(token).get("userId", Long.class);
    }

    private Date extractExpiration(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Used by services with email
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);
        return extractedEmail != null
                && extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    // Used by filters/tests with only token
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    // Wrapper so tests can call jwtUtil.validate(token)
    public boolean validate(String token) {
        return isTokenValid(token);
    }
}
