// MatchAttemptService.java
package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;
import java.util.List;

public interface MatchAttemptService {
    MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt);
    MatchAttemptRecord updateAttemptStatus(Long id, MatchAttemptRecord.Status status);
    List<MatchAttemptRecord> getAllMatchAttempts();
    List<MatchAttemptRecord> getAttemptsByStudent(Long studentId);
}
