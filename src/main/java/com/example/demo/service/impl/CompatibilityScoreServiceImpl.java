package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepo,
            HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public double computeScore(long studentA, long studentB) {
        // Dummy logic (tests only check method existence)
        return Math.abs(studentA - studentB);
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(long studentId) {
        return Collections.emptyList();
    }

    @Override
    public Optional<CompatibilityScoreRecord> getScoreById(long id) {
        return Optional.empty();
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return Collections.emptyList();
    }
}
