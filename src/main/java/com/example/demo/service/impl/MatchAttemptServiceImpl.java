package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository matchRepo;

    public MatchAttemptServiceImpl(MatchAttemptRecordRepository matchRepo,
                                   CompatibilityScoreRecordRepository scoreRepo) {
        this.matchRepo = matchRepo;
    }

@Override
public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
    if (attempt.getResultScoreId() != null) {
        attempt.setStatus(MatchAttemptRecord.Status.MATCHED);
    } else {
        attempt.setStatus(MatchAttemptRecord.Status.PENDING);
    }
    return matchRepo.save(attempt);
}
    @Override
    public MatchAttemptRecord updateAttemptStatus(Long id, MatchAttemptRecord.Status status) {
        MatchAttemptRecord a = matchRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Match attempt not found"));
        a.setStatus(status.name());
        return matchRepo.save(a);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return matchRepo.findAll();
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return matchRepo.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }
}
