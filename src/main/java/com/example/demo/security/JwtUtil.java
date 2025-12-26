import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;

private final SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes());

public String generateToken(String username, String role, String email, String userId) {
    return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .claim("email", email)
            .claim("userId", userId)
            .signWith(key)    // non‑deprecated for 0.11.x
            .compact();
}

public Claims parseToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key)          // or verifyWith(key) in newer versions
            .build()
            .parseClaimsJws(token)
            .getBody();
}
