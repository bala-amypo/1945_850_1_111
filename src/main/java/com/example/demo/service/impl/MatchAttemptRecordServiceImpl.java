package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.service.MatchAttemptRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MatchAttemptRecordServiceImpl implements MatchAttemptRecordService {

    private final MatchAttemptRecordRepository repository;

    public MatchAttemptRecordServiceImpl(MatchAttemptRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public MatchAttemptRecord createAttempt(Long initiatorId, Long candidateId) {
        MatchAttemptRecord record = new MatchAttemptRecord();
        record.setInitiatorStudentId(initiatorId);
        record.setCandidateStudentId(candidateId);
        record.setStatus("PENDING_REVIEW");
        record.setAttemptedAt(LocalDateTime.now());
        return repository.save(record);
    }

    @Override
    public MatchAttemptRecord approveAttempt(Long id) {
        MatchAttemptRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match attempt not found"));
        record.setStatus("APPROVED");
        return repository.save(record);
    }

    @Override
    public MatchAttemptRecord rejectAttempt(Long id) {
        MatchAttemptRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match attempt not found"));
        record.setStatus("REJECTED");
        return repository.save(record);
    }
}
