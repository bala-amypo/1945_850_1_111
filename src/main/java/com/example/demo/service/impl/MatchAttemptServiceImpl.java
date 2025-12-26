package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {
    
    private final MatchAttemptRecordRepository matchAttemptRecordRepository;
    private final CompatibilityScoreRecordRepository scoreRepository;
    
    public MatchAttemptServiceImpl(MatchAttemptRecordRepository matchAttemptRecordRepository,
            CompatibilityScoreRecordRepository scoreRepository) {
        this.matchAttemptRecordRepository = matchAttemptRecordRepository;
        this.scoreRepository = scoreRepository;
    }
    
    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
        if (attempt.getResultScoreId() != null) {
            // Check if score exists
            scoreRepository.findById(attempt.getResultScoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
            attempt.setStatus(MatchAttemptRecord.Status.MATCHED);
        } else {
            attempt.setStatus(MatchAttemptRecord.Status.PENDING_REVIEW);
        }
        
        return matchAttemptRecordRepository.save(attempt);
    }
    
    @Override
    public MatchAttemptRecord updateAttemptStatus(Long id, String status) {
        MatchAttemptRecord attempt = getAttemptById(id);
        attempt.setStatus(MatchAttemptRecord.Status.valueOf(status));
        return matchAttemptRecordRepository.save(attempt);
    }
    
    @Override
    public MatchAttemptRecord getAttemptById(Long id) {
        return matchAttemptRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match attempt not found"));
    }
    
    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return matchAttemptRecordRepository.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }
    
    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return matchAttemptRecordRepository.findAll();
    }
}