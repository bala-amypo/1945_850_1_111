// src/main/java/com/example/demo/service/impl/HabitProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private HabitProfileRepository habitRepo;
    private StudentProfileRepository studentRepo;

    // No-arg constructor for Spring (fixes "No default constructor" error)
    public HabitProfileServiceImpl() {
    }

    // Constructor used by tests (HabitProfileServiceImpl(habitRepo))
    public HabitProfileServiceImpl(HabitProfileRepository habitRepo) {
        this.habitRepo = habitRepo;
    }

    // Constructor used by Spring normal DI
    public HabitProfileServiceImpl(HabitProfileRepository habitRepo,
                                   StudentProfileRepository studentRepo) {
        this.habitRepo = habitRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() <= 0) {
            throw new IllegalArgumentException("study hours must be positive");
        }

        if (studentRepo != null &&
            studentRepo.findById(habit.getStudentId()).isEmpty()) {
            throw new ResourceNotFoundException("Student not found");
        }

        Optional<HabitProfile> existing = habitRepo.findByStudentId(habit.getStudentId());
        if (existing.isPresent()) {
            HabitProfile h = existing.get();
            h.setStudyHoursPerDay(habit.getStudyHoursPerDay());
            h.setSleepSchedule(habit.getSleepSchedule());
            h.setCleanlinessLevel(habit.getCleanlinessLevel());
            h.setNoiseTolerance(habit.getNoiseTolerance());
            h.setSocialPreference(habit.getSocialPreference());
            return habitRepo.save(h);
        }
        return habitRepo.save(habit);
    }

    @Override
    public Optional<HabitProfile> getHabitByStudent(Long studentId) {
        return habitRepo.findByStudentId(studentId);
    }

    @Override
    public Optional<HabitProfile> getHabitById(Long id) {
        return habitRepo.findById(id);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitRepo.findAll();
    }
}
