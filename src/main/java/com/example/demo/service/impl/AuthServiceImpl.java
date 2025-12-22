package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse register(AuthRequest request) {
        
        return new AuthResponse(
                "generated-token-123",   // token
                1L,                     // userId (dummy)
                request.getEmail(),     // email
                "STUDENT"               // role
        );
    }

    @Override
    public AuthResponse login(AuthRequest request) {
     
        return new AuthResponse(
                "generated-token-123",   // token
                1L,                      // userId (dummy)
                request.getEmail(),      // email
                "STUDENT"                // role
        );
    }
}
