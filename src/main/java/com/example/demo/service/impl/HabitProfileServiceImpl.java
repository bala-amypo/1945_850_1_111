package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository repo;

    public HabitProfileServiceImpl(HabitProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile h) {
        h.setUpdatedAt(LocalDateTime.now());
        return repo.save(h);
    }

    @Override
    public Optional<HabitProfile> getHabitById(long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<HabitProfile> getHabitByStudent(long studentId) {
        return repo.findByStudentId(studentId);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return repo.findAll();
    }
}
