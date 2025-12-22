package com.example.demo.service.impl;

import com.example.demo.dto.CompatibilityScoreDto;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    @Autowired
    private CompatibilityScoreRecordRepository compatibilityRepository;

    @Override
    public CompatibilityScoreDto computeCompatibilityScore(Long studentAId, Long studentBId) {
        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setScore(75.5);
        record.setCompatibilityLevel("HIGH");
        // optionally set detailsJson / computedAt here

        CompatibilityScoreRecord saved = compatibilityRepository.save(record);
        return mapToDto(saved);
    }

    @Override
    public List<CompatibilityScoreDto> getAllScores() {
        return compatibilityRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompatibilityScoreDto> getScoreById(Long id) {
        return compatibilityRepository.findById(id)
                .map(this::mapToDto);
    }

    @Override
    public List<CompatibilityScoreDto> getScoresForStudent(Long studentId) {
        // placeholder: currently just returns all scores
        return compatibilityRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CompatibilityScoreDto mapToDto(CompatibilityScoreRecord score) {
        return new CompatibilityScoreDto(
                score.getId(),
                score.getScore(),
                score.getCompatibilityLevel(),
                score.getDetailsJson(),
                score.getComputedAt()
        );
    }
}
