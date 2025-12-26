// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.security.JwtUtil;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     private final JwtUtil jwtUtil;

//     public AuthController(JwtUtil jwtUtil) {
//         this.jwtUtil = jwtUtil;
//     }

//     // Main simple register endpoint: used by app code
//     @PostMapping("/register")
//     public ResponseEntity<String> register(
//             @RequestParam String username,
//             @RequestParam String email) {

//         Long userId = 0L; // no real DB id here
//         String token = jwtUtil.generateToken(username, userId, email);
//         return ResponseEntity.ok(token);
//     }

//     // Overload so tests can call register(AuthRequest)
//     public ResponseEntity<String> register(AuthRequest request) {
//         return register(request.getUsername(), request.getEmail());
//     }

//     // Simple login endpoint
//     @PostMapping("/login")
//     public ResponseEntity<String> login(
//             @RequestParam String username,
//             @RequestParam String email) {

//         Long userId = 0L;
//         String token = jwtUtil.generateToken(username, userId, email);
//         return ResponseEntity.ok(token);
//     }
// }
