// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.*;
// import com.example.demo.repository.CompatibilityScoreRecordRepository;
// import com.example.demo.repository.HabitProfileRepository;
// import com.example.demo.service.CompatibilityScoreService;
// import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {
    
//     private final CompatibilityScoreRecordRepository compatibilityScoreRecordRepository;
//     private final HabitProfileRepository habitProfileRepository;
    
//     public CompatibilityScoreServiceImpl(
//             CompatibilityScoreRecordRepository compatibilityScoreRecordRepository,
//             HabitProfileRepository habitProfileRepository) {
//         this.compatibilityScoreRecordRepository = compatibilityScoreRecordRepository;
//         this.habitProfileRepository = habitProfileRepository;
//     }
    
//     @Override
//     public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
//         if (studentAId.equals(studentBId)) {
//             throw new IllegalArgumentException("same student");
//         }
        
//         HabitProfile habitA = habitProfileRepository.findByStudentId(studentAId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));
        
//         HabitProfile habitB = habitProfileRepository.findByStudentId(studentBId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));
        
//         double score = calculateCompatibilityScore(habitA, habitB);
        
//         Optional<CompatibilityScoreRecord> existing = 
//                 compatibilityScoreRecordRepository.findByStudentAIdAndStudentBId(studentAId, studentBId);
        
//         if (existing.isPresent()) {
//             CompatibilityScoreRecord existingScore = existing.get();
//             existingScore.setScore(score);
//             existingScore.setComputedAt(LocalDateTime.now());
//             existingScore.setCompatibilityLevel(getCompatibilityLevel(score));
//             return compatibilityScoreRecordRepository.save(existingScore);
//         }
        
//         CompatibilityScoreRecord scoreRecord = new CompatibilityScoreRecord();
//         scoreRecord.setStudentAId(studentAId);
//         scoreRecord.setStudentBId(studentBId);
//         scoreRecord.setScore(score);
//         scoreRecord.setCompatibilityLevel(getCompatibilityLevel(score));
//         scoreRecord.setComputedAt(LocalDateTime.now());
        
//         return compatibilityScoreRecordRepository.save(scoreRecord);
//     }
    
//     @Override
//     public CompatibilityScoreRecord getScoreById(Long id) {
//         return compatibilityScoreRecordRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
//     }
    
//     @Override
//     public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
//         return compatibilityScoreRecordRepository.findByStudentAIdOrStudentBId(studentId, studentId);
//     }
    
//     @Override
//     public List<CompatibilityScoreRecord> getAllScores() {
//         return compatibilityScoreRecordRepository.findAll();
//     }
    
//     private double calculateCompatibilityScore(HabitProfile habitA, HabitProfile habitB) {
//         double score = 50.0;
        
//         if (habitA.getSleepSchedule() == habitB.getSleepSchedule()) {
//             score += 20;
//         }
        
//         if (habitA.getCleanlinessLevel() == habitB.getCleanlinessLevel()) {
//             score += 15;
//         }
        
//         if (habitA.getNoiseTolerance() == habitB.getNoiseTolerance()) {
//             score += 15;
//         }
        
//         if (habitA.getSocialPreference() == habitB.getSocialPreference()) {
//             score += 15;
//         }
        
//         int studyDiff = Math.abs(habitA.getStudyHoursPerDay() - habitB.getStudyHoursPerDay());
//         if (studyDiff <= 1) {
//             score += 10;
//         } else if (studyDiff <= 2) {
//             score += 5;
//         }
        
//         return Math.max(0, Math.min(100, score));
//     }
    
//     private CompatibilityScoreRecord.CompatibilityLevel getCompatibilityLevel(double score) {
//         if (score >= 90) {
//             return CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT;
//         } else if (score >= 70) {
//             return CompatibilityScoreRecord.CompatibilityLevel.GOOD;
//         } else if (score >= 50) {
//             return CompatibilityScoreRecord.CompatibilityLevel.MEDIUM;
//         } else if (score >= 30) {
//             return CompatibilityScoreRecord.CompatibilityLevel.FAIR;
//         } else {
//             return CompatibilityScoreRecord.CompatibilityLevel.POOR;
//         }
//     }
// }