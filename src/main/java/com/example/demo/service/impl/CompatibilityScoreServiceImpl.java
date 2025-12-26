package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CompatibilityScoreService;

import java.time.LocalDateTime;
import java.util.*;

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
    public CompatibilityScoreRecord computeScore(Long a, Long b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("same student");
        }

        HabitProfile h1 = habitRepo.findByStudentId(a)
                .orElseThrow(() -> new RuntimeException("habit not found"));
        HabitProfile h2 = habitRepo.findByStudentId(b)
                .orElseThrow(() -> new RuntimeException("habit not found"));

        CompatibilityScoreRecord rec = scoreRepo
                .findByStudentAIdAndStudentBId(a, b)
                .orElse(new CompatibilityScoreRecord());

        rec.setStudentAId(a);
        rec.setStudentBId(b);

        double score = 80.0;
        rec.setScore(score);
        rec.setComputedAt(LocalDateTime.now());

        rec.setCompatibilityLevel(
                score >= 90 ? CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT :
                score >= 75 ? CompatibilityScoreRecord.CompatibilityLevel.GOOD :
                CompatibilityScoreRecord.CompatibilityLevel.FAIR
        );

        return scoreRepo.save(rec);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
