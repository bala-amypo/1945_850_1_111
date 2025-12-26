
// package com.example.demo.service.impl;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.UserAccount;
// import com.example.demo.model.Role;
// import com.example.demo.repository.UserAccountRepository;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.AuthService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class AuthServiceImpl implements AuthService {
    
//     private final UserAccountRepository userAccountRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;
    
//     public AuthServiceImpl(UserAccountRepository userAccountRepository,
//             PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
//         this.userAccountRepository = userAccountRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.jwtUtil = jwtUtil;
//     }
    
//     @Override
//     public AuthResponse register(AuthRequest request) {
//         if (userAccountRepository.findByEmail(request.getEmail()).isPresent()) {
//             throw new IllegalArgumentException("Email already exists");
//         }
        
//         if (request.getPassword() == null || request.getPassword().isEmpty()) {
//             throw new IllegalArgumentException("Password cannot be empty");
//         }
        
//         UserAccount userAccount = new UserAccount();
//         userAccount.setEmail(request.getEmail());
//         userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
//         userAccount.setRole(Role.STUDENT);
//         userAccount.setActive(true);
        
//         userAccount = userAccountRepository.save(userAccount);
        
//         String token = jwtUtil.generateToken(userAccount.getEmail(), userAccount.getId(), 
//                 userAccount.getRole().toString());
        
//         return new AuthResponse("Registration successful", token, userAccount.getId(), 
//                 userAccount.getEmail(), userAccount.getRole().toString());
//     }
    
//     @Override
//     public AuthResponse login(AuthRequest request) {
//         UserAccount userAccount = userAccountRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
//         if (!passwordEncoder.matches(request.getPassword(), userAccount.getPassword())) {
//             throw new IllegalArgumentException("Invalid credentials");
//         }
        
//         if (!userAccount.isEnabled()) {
//             throw new IllegalArgumentException("User account is inactive");
//         }
        
//         String token = jwtUtil.generateToken(userAccount.getEmail(), userAccount.getId(), 
//                 userAccount.getRole().toString());
        
//         return new AuthResponse("Login successful", token, userAccount.getId(), 
//                 userAccount.getEmail(), userAccount.getRole().toString());
//     }
// }
