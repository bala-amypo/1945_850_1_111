package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class AuthController {

    private final JwtUtil jwtUtil;
    private final Map<String, String> users = new HashMap<>();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<String> register(AuthRequest request) {

        if (users.containsKey(request.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        users.put(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Registered");
    }
}
