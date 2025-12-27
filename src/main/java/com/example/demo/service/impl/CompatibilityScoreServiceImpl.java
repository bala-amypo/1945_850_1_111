package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            throw new IllegalArgumentException("Cannot match same student");
        }
        
        HabitProfile habitA = habitRepo.findByStudentId(studentAId)
            .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student A"));
        HabitProfile habitB = habitRepo.findByStudentId(studentBId)
            .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found for student B"));
        
        Optional<CompatibilityScoreRecord> existing = scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);
        
        double score = calculateCompatibilityScore(habitA, habitB);
        CompatibilityScoreRecord.CompatibilityLevel level = determineCompatibilityLevel(score);
        
        CompatibilityScoreRecord record;
        if (existing.isPresent()) {
            record = existing.get();
        } else {
            record = new CompatibilityScoreRecord();
            record.setStudentAId(studentAId);
            record.setStudentBId(studentBId);
        }
        
        record.setScore(score);
        record.setCompatibilityLevel(level);
        record.setComputedAt(LocalDateTime.now());
        record.setDetailsJson("{\"computed\":true}");
        
        return scoreRepo.save(record);
    }
    
    private double calculateCompatibilityScore(HabitProfile a, HabitProfile b) {
        double score = 0;
        int factors = 0;
        
        if (a.getSleepSchedule() != null && b.getSleepSchedule() != null) {
            score += a.getSleepSchedule().equals(b.getSleepSchedule()) ? 25 : 0;
            factors++;
        }
        
        if (a.getCleanlinessLevel() != null && b.getCleanlinessLevel() != null) {
            score += a.getCleanlinessLevel().equals(b.getCleanlinessLevel()) ? 25 : 0;
            factors++;
        }
        
        if (a.getNoiseTolerance() != null && b.getNoiseTolerance() != null) {
            score += a.getNoiseTolerance().equals(b.getNoiseTolerance()) ? 25 : 0;
            factors++;
        }
        
        if (a.getSocialPreference() != null && b.getSocialPreference() != null) {
            score += a.getSocialPreference().equals(b.getSocialPreference()) ? 25 : 0;
            factors++;
        }
        
        return factors > 0 ? score : 50.0;
    }
    
    private CompatibilityScoreRecord.CompatibilityLevel determineCompatibilityLevel(double score) {
        if (score >= 90) return CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT;
        if (score >= 70) return CompatibilityScoreRecord.CompatibilityLevel.HIGH;
        if (score >= 50) return CompatibilityScoreRecord.CompatibilityLevel.MEDIUM;
        return CompatibilityScoreRecord.CompatibilityLevel.LOW;
    }
    
    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
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