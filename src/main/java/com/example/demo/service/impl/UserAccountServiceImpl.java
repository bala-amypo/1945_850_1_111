package com.example.demo.service.impl;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @Override
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }
    
    @Override
    public Optional<UserAccount> getUserById(Long id) {
        return userAccountRepository.findById(id);
    }
    
    @Override
    public void deleteUser(Long id) {
        userAccountRepository.deleteById(id);
    }
}
