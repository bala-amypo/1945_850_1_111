package com.example.demo.service;

import com.example.demo.model.UserAccount;
import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    List<UserAccount> getAllUsers();
    Optional<UserAccount> getUserById(Long id);
    void deleteUser(Long id);
}
