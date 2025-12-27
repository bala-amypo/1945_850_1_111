package com.example.demo.service.impl;

import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepo,
            HabitProfileRepository habitRepo) {
    }

    @Override
    public double calculateScore(long studentA, long studentB) {
        return 0.0;
    }
}
