package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityScoreServiceImpl {

    private final HabitProfileRepository habitProfileRepository;

    public CompatibilityScoreServiceImpl(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    public double calculateScore(Long studentId1, Long studentId2) {

        List<HabitProfile> list1 = habitProfileRepository.findByStudentId(studentId1);
        List<HabitProfile> list2 = habitProfileRepository.findByStudentId(studentId2);

        if (list1.isEmpty() || list2.isEmpty()) {
            throw new RuntimeException("Habit profile not found");
        }

        HabitProfile h1 = list1.get(0);
        HabitProfile h2 = list2.get(0);

        // Simple compatibility logic
        return Math.abs(h1.getStudyHoursPerDay() - h2.getStudyHoursPerDay());
    }
}
