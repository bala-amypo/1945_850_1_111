package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Map<String, UserAccount> internalUsers = new HashMap<>();

    public AuthController() {}

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        if (userAccountRepository != null) {
            Optional<UserAccount> existing = userAccountRepository.findByEmail(request.getEmail());
            if (existing.isPresent()) {
                AuthResponse response = new AuthResponse();
                response.setStatusCodeValue(400);
                response.setMessage("Email already exists");
                return response;
            }

            UserAccount user = new UserAccount();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole() != null ? request.getRole() : "STUDENTVIEWER");
            user.setActive(true);

            UserAccount saved = userAccountRepository.save(user);
            return new AuthResponse(200, null, saved.getId(), saved.getEmail(), saved.getRole());
        } else {
            Optional<UserAccount> existing = internalUsers.values().stream()
                    .filter(u -> u.getEmail().equals(request.getEmail()))
                    .findFirst();

            if (existing.isPresent()) {
                AuthResponse response = new AuthResponse();
                response.setStatusCodeValue(400);
                response.setMessage("Email already exists");
                return response;
            }

            UserAccount user = new UserAccount();
            user.setId((long) internalUsers.size() + 1);
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole() != null ? request.getRole() : "STUDENTVIEWER");
            user.setActive(true);

            internalUsers.put(request.getEmail(), user);
            return new AuthResponse(200, null, user.getId(), user.getEmail(), user.getRole());
        }
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Optional<UserAccount> user = userAccountRepository.findByEmail(request.getEmail());
        
        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get().getEmail(), user.get().getRole(), 
                                                user.get().getEmail(), user.get().getId().toString());
            return new AuthResponse(200, token, user.get().getId(), user.get().getEmail(), user.get().getRole());
        }

        return new AuthResponse(401, null, null, null, null);
    }
}
