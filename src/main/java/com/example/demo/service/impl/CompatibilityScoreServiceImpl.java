package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public CompatibilityScoreRecord computeScore(long a, long b) {

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(a);
        record.setStudentBId(b);
        record.setScore(75.0);
        record.setCompatibilityLevel(
                CompatibilityScoreRecord.CompatibilityLevel.GOOD
        );

        return scoreRepo.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(long id) {
        return scoreRepo.findById(id).orElse(null);
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(long studentId) {
        return scoreRepo.findAll()
                .stream()
                .filter(r ->
                        r.getStudentAId() == studentId ||
                        r.getStudentBId() == studentId
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
