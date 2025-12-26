package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {
    
    private final JwtUtil jwtUtil;
    private final Map<String, AuthRequest> users = new HashMap<>();
    
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        if (users.containsKey(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AuthResponse("User already exists", null));
        }
        
        users.put(request.getUsername(), request);
        String token = jwtUtil.generateToken(request.getUsername(), 
            request.getRole() != null ? request.getRole() : "USER", 
            request.getEmail() != null ? request.getEmail() : request.getUsername() + "@example.com", 
            "1");
        
        return ResponseEntity.ok(new AuthResponse("Registration successful", token));
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthRequest user = users.get(request.getUsername());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse("Invalid credentials", null));
        }
        
        String token = jwtUtil.generateToken(request.getUsername(), 
            user.getRole() != null ? user.getRole() : "USER", 
            user.getEmail() != null ? user.getEmail() : request.getUsername() + "@example.com", 
            "1");
        
        return ResponseEntity.ok(new AuthResponse("Login successful", token));
    }
}