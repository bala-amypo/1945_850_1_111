package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {
    
    private final JwtUtil jwtUtil;
    private final Map<String, AuthRequest> users = new HashMap<>();
    
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (users.containsKey(request.getUsername())) {
            return ResponseEntity.badRequest()
                .body(new AuthResponse(null, "User already exists"));
        }
        
        users.put(request.getUsername(), request);
        String token = jwtUtil.generateToken(request.getUsername(), 
            request.getRole() != null ? request.getRole() : "STUDENT_VIEWER", 
            request.getEmail(), "1");
        
        return ResponseEntity.ok(new AuthResponse(token, "Registration successful"));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthRequest user = users.get(request.getUsername());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest()
                .body(new AuthResponse(null, "Invalid credentials"));
        }
        
        String token = jwtUtil.generateToken(request.getUsername(), 
            user.getRole() != null ? user.getRole() : "STUDENT_VIEWER", 
            user.getEmail(), "1");
        
        return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
    }
}