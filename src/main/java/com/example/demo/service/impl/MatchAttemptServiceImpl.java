// CompatibilityScoreService.java
package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;
import java.util.List;

public interface CompatibilityScoreService {
    CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId);
    List<CompatibilityScoreRecord> getScoresForStudent(Long studentId);
    CompatibilityScoreRecord getScoreById(Long id);
    List<CompatibilityScoreRecord> getAllScores();
}

// CompatibilityScoreServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {
    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo, HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("cannot compute score for same student");
        }

        HabitProfile habitA = habitRepo.findByStudentId(studentAId)
            .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student " + studentAId));
        HabitProfile habitB = habitRepo.findByStudentId(studentBId)
            .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student " + studentBId));

        double score = calculateCompatibilityScore(habitA, habitB);

        CompatibilityScoreRecord record = scoreRepo.findByStudentAIdAndStudentBId(
            Math.min(studentAId, studentBId), Math.max(studentAId, studentBId))
            .orElse(new CompatibilityScoreRecord());
        
        record.setStudentAId(Math.min(studentAId, studentBId));
        record.setStudentBId(Math.max(studentAId, studentBId));
        record.setScore(score);
        record.setCompatibilityLevel(getCompatibilityLevel(score));
        record.setDetailsJson("{\"similarity\":\"high\"}");

        return scoreRepo.save(record);
    }

    private double calculateCompatibilityScore(HabitProfile a, HabitProfile b) {
        double score = 50.0; // base score

        // Study hours similarity
        if (a.getStudyHoursPerDay() != null && b.getStudyHoursPerDay() != null) {
            double studyDiff = Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay());
            score += (1.0 - studyDiff / 10.0) * 20;
        }

        // Enum similarities (simplified)
        if (a.getSleepSchedule() == b.getSleepSchedule()) score += 10;
        if (a.getCleanlinessLevel() == b.getCleanlinessLevel()) score += 10;
        if (a.getNoiseTolerance() == b.getNoiseTolerance()) score += 10;

        return Math.max(0, Math.min(100, score));
    }

    private CompatibilityScoreRecord.CompatibilityLevel getCompatibilityLevel(double score) {
        if (score >= 90) return CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT;
        if (score >= 70) return CompatibilityScoreRecord.CompatibilityLevel.GOOD;
        if (score >= 50) return CompatibilityScoreRecord.CompatibilityLevel.FAIR;
        return CompatibilityScoreRecord.CompatibilityLevel.POOR;
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Compatibility score not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
