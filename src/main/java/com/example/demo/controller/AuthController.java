package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Simple register endpoint – adjust URL if your tests expect a different path
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String username,
            @RequestParam String email) {

        // If you do not have a real DB user yet, just use 0L as dummy id
        Long userId = 0L;

        String token = jwtUtil.generateToken(username, userId, email);
        return ResponseEntity.ok(token);
    }

    // Simple login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String email) {

        Long userId = 0L;

        String token = jwtUtil.generateToken(username, userId, email);
        return ResponseEntity.ok(token);
    }
}
