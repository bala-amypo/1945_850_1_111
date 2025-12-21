package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository attemptRepository;
    private final CompatibilityScoreRecordRepository scoreRepository;

    public MatchAttemptServiceImpl(MatchAttemptRecordRepository attemptRepository,
                                   CompatibilityScoreRecordRepository scoreRepository) {
        this.attemptRepository = attemptRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
        CompatibilityScoreRecord score = scoreRepository.findById(attempt.getResultScoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Match not found"));

        attempt.setStatus(score.getScore() >= 60 ? "MATCHED" : "NOT_COMPATIBLE");
        return attemptRepository.save(attempt);
    }

    @Override
    public MatchAttemptRecord getAttemptById(Long id) {
        return attemptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found"));
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return attemptRepository.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
    public MatchAttemptRecord updateAttemptStatus(Long attemptId, String status) {
        MatchAttemptRecord attempt = getAttemptById(attemptId);
        attempt.setStatus(status);
        return attemptRepository.save(attempt);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return attemptRepository.findAll();
    }
}
