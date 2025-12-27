package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private CompatibilityScoreRecordRepository scoreRepo;
    private HabitProfileRepository habitRepo;

    @Autowired
    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo, 
                                       HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Error: cannot match same student");
        }

        HabitProfile habitA = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student A"));
        HabitProfile habitB = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student B"));

        double score = calculateCompatibilityScore(habitA, habitB);

        Optional<CompatibilityScoreRecord> existing = scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);
        CompatibilityScoreRecord record;
        
        if (existing.isPresent()) {
            record = existing.get();
            record.setScore(score);
            record.setComputedAt(LocalDateTime.now());
        } else {
            record = new CompatibilityScoreRecord();
            record.setStudentAId(studentAId);
            record.setStudentBId(studentBId);
            record.setScore(score);
            record.setComputedAt(LocalDateTime.now());
        }

        return scoreRepo.save(record);
    }

    private double calculateCompatibilityScore(HabitProfile a, HabitProfile b) {
        double score = 50.0;

        if (a.getSleepSchedule() != null && b.getSleepSchedule() != null && 
            a.getSleepSchedule().equals(b.getSleepSchedule())) {
            score += 10;
        }

        if (a.getCleanlinessLevel() != null && b.getCleanlinessLevel() != null && 
            a.getCleanlinessLevel().equals(b.getCleanlinessLevel())) {
            score += 10;
        }

        if (a.getNoiseTolerance() != null && b.getNoiseTolerance() != null && 
            a.getNoiseTolerance().equals(b.getNoiseTolerance())) {
            score += 10;
        }

        if (a.getSocialPreference() != null && b.getSocialPreference() != null && 
            a.getSocialPreference().equals(b.getSocialPreference())) {
            score += 10;
        }

        if ((a.getSmoking() != null && b.getSmoking() != null && a.getSmoking().equals(b.getSmoking())) ||
            (a.getSmoking() == null && b.getSmoking() == null)) {
            score += 5;
        }

        if ((a.getDrinking() != null && b.getDrinking() != null && a.getDrinking().equals(b.getDrinking())) ||
            (a.getDrinking() == null && b.getDrinking() == null)) {
            score += 5;
        }

        return Math.min(score, 100.0);
    }

    @Override
public CompatibilityScoreRecord getScoreById(Long id) {
    return scoreRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Compatibility score not found"));
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
