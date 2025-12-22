package com.example.demo.service;

import com.example.demo.dto.CompatibilityScoreDto;
import java.util.List;
import java.util.Optional;

public interface CompatibilityScoreService {
    CompatibilityScoreDto computeCompatibilityScore(Long studentAId, Long studentBId);
    List<CompatibilityScoreDto> getAllScores();
    Optional<CompatibilityScoreDto> getScoreById(Long id);
    List<CompatibilityScoreDto> getScoresForStudent(Long studentId);
}
