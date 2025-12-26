package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;

import java.time.LocalDateTime;
import java.util.*;

public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository repo;

    public HabitProfileServiceImpl(HabitProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours invalid");
        }

        Optional<HabitProfile> existing = repo.findByStudentId(habit.getStudentId());
        if (existing.isPresent()) {
            habit.setId(existing.get().getId());
        }

        habit.setUpdatedAt(LocalDateTime.now());
        return repo.save(habit);
    }

    @Override
    public Optional<HabitProfile> getHabitById(Long id) {
        return repo.findById(id);
    }

    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return repo.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("habit not found"));
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return repo.findAll();
    }
}
