package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    // IMPORTANT: parameter type must be String, not Object
    Optional<UserAccount> findByEmail(String email);

    boolean existsByEmail(String email);
}
