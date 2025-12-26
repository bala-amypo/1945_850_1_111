package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.MatchAttemptService;

import java.util.*;

public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository repo;
    private final CompatibilityScoreRecordRepository scoreRepo;

    public MatchAttemptServiceImpl(
            MatchAttemptRecordRepository repo,
            CompatibilityScoreRecordRepository scoreRepo) {
        this.repo = repo;
        this.scoreRepo = scoreRepo;
    }

    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
        if (attempt.getResultScoreId() != null &&
                scoreRepo.findById(attempt.getResultScoreId()).isPresent()) {
            attempt.setStatus(MatchAttemptRecord.Status.MATCHED);
        } else {
            attempt.setStatus(MatchAttemptRecord.Status.PENDING_REVIEW);
        }
        return repo.save(attempt);
    }

    @Override
    public MatchAttemptRecord updateAttemptStatus(Long id, String status) {
        MatchAttemptRecord a = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        a.setStatus(MatchAttemptRecord.Status.valueOf(status));
        return repo.save(a);
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return repo.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return repo.findAll();
    }
}
