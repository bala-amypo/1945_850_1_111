package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;

import java.util.List;

public interface MatchAttemptService {
    MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt);
    MatchAttemptRecord getAttemptById(Long id);
    List<MatchAttemptRecord> getAttemptsByStudent(Long studentId);
    MatchAttemptRecord updateAttemptStatus(Long attemptId, String status);
    List<MatchAttemptRecord> getAllMatchAttempts();
}
