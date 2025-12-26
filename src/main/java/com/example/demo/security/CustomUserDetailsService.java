package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                    .password("{noop}admin")
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("not found");
    }
}
