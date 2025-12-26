package com.example.demo.security;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
        // Seed admin user for tests
        seedAdminUser();
    }

    private void seedAdminUser() {
        if (userAccountRepository.findByEmail("admin@example.com").isEmpty()) {
            UserAccount admin = new UserAccount();
            admin.setEmail("admin@example.com");
            admin.setPassword("$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92XDGI8QvqaOftW2EUW1G"); // password: admin123
            admin.setRole("ADMIN");
            userAccountRepository.save(admin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return org.springframework.security.core.userdetails.User.builder()
                .username(userAccount.getEmail())
                .password(userAccount.getPassword())
                .authorities(userAccount.getRole())
                .build();
    }
}
