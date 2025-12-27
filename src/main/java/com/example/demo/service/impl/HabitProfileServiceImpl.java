package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository repo;

    public HabitProfileServiceImpl(HabitProfileRepository repo) {
        this.repo = repo;
    }

    public HabitProfile createOrUpdateHabit(HabitProfile h) {
        if (h.getStudyHoursPerDay() < 0)
            throw new IllegalArgumentException("study hours");

        Optional<HabitProfile> existing = repo.findByStudentId(h.getStudentId());
        if (existing.isPresent()) {
            h.setId(existing.get().getId());
        }

        h.setUpdatedAt(LocalDateTime.now());
        return repo.save(h);
    }

    public HabitProfile getHabitByStudent(Long studentId) {
        return repo.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public Optional<HabitProfile> getHabitById(Long id) {
        return repo.findById(id);
    }

    public List<HabitProfile> getAllHabitProfiles() {
        return repo.findAll();
    }
}
