// src/main/java/com/example/demo/security/CustomUserDetailsService.java
package com.example.demo.security;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserAccountRepository userAccountRepository;

    // NO-ARG constructor for tests (line 569 fix)
    public CustomUserDetailsService() {
        // empty for test instantiation
    }

    // Spring constructor injection
    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userAccountRepository == null) {
            // For tests without repo
            throw new UsernameNotFoundException("User not found: " + email);
        }
        
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(userAccount.getEmail())
                .password(userAccount.getPassword())
                .authorities(userAccount.getRole())
                .build();
    }
}
