package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository repository;

    public HabitProfileServiceImpl(HabitProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile h) {
        h.setUpdatedAt(LocalDateTime.now());
        return repository.save(h);
    }

    @Override
    public HabitProfile getHabitById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<HabitProfile> getHabitByStudent(long studentId) {
        return repository.findByStudentId(studentId);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return repository.findAll();
    }
}
