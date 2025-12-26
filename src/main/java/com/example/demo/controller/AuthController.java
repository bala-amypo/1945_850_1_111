package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        // create user (implementation depends on your AuthService)
        User user = authService.register(request);

        // user just created – has an id
        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getId(),          // Long
                user.getEmail()
        );

        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // validate credentials and load user
        User user = authService.login(request);

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getId(),          // Long
                user.getEmail()
        );

        return ResponseEntity.ok(token);
    }
}
