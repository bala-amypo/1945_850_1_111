package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;

import java.util.List;
import java.util.Optional;

public interface CompatibilityScoreService {

    double computeScore(long studentA, long studentB);

    List<CompatibilityScoreRecord> getScoresForStudent(long studentId);

    Optional<CompatibilityScoreRecord> getScoreById(long id);

    List<CompatibilityScoreRecord> getAllScores();
}
