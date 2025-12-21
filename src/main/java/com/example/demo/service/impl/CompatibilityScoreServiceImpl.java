package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepository;
    private final StudentProfileRepository studentRepository;
    private final HabitProfileRepository habitRepository;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepository,
                                         StudentProfileRepository studentRepository,
                                         HabitProfileRepository habitRepository) {
        this.scoreRepository = scoreRepository;
        this.studentRepository = studentRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }

        StudentProfile a = studentRepository.findById(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        StudentProfile b = studentRepository.findById(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (!a.isActive() || !b.isActive()) {
            throw new IllegalArgumentException("both students must be active");
        }

        HabitProfile ha = habitRepository.findByStudentId(a.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        HabitProfile hb = habitRepository.findByStudentId(b.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double score = 100.0;

        if (!ha.getSleepSchedule().equals(hb.getSleepSchedule())) score -= 20;
        if (!ha.getCleanlinessLevel().equals(hb.getCleanlinessLevel())) score -= 20;
        if (!ha.getNoiseTolerance().equals(hb.getNoiseTolerance())) score -= 20;
        if (!ha.getSocialPreference().equals(hb.getSocialPreference())) score -= 20;

        if (score < 0) score = 0;
        if (score > 100) score = 100;

        String level = score >= 80 ? "EXCELLENT"
                : score >= 60 ? "HIGH"
                : score >= 40 ? "MEDIUM" : "LOW";

        CompatibilityScoreRecord record = scoreRepository
                .findByStudentAIdAndStudentBId(studentAId, studentBId)
                .orElse(new CompatibilityScoreRecord());

        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(score);
        record.setCompatibilityLevel(level);
        record.setDetailsJson("{}");

        return scoreRepository.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepository.findByStudentAIdOrStudentBIdOrderByScoreDesc(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}
