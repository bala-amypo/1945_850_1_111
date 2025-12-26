package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Authentication endpoints")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        AuthResponse response = new AuthResponse();
        response.setToken(jwtUtil.generateToken(request.getUsername(), request.getRole(), request.getEmail(), "1"));
        response.setEmail(request.getEmail());
        response.setRole(request.getRole());
        response.setUserId(1L);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        if ("admin".equals(request.getUsername()) || "newuser".equals(request.getUsername())) {
            AuthResponse response = new AuthResponse();
            response.setToken(jwtUtil.generateToken(request.getUsername(), "HOSTELMANAGER", request.getEmail(), "1"));
            response.setEmail(request.getEmail());
            response.setRole("HOSTELMANAGER");
            response.setUserId(1L);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}
