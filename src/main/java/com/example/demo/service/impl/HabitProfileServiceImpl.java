package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private HabitProfileRepository habitRepo;

    @Autowired
    public HabitProfileServiceImpl(HabitProfileRepository habitRepo) {
        this.habitRepo = habitRepo;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("Error: study hours must be positive");
        }
        
        Optional<HabitProfile> existing = habitRepo.findByStudentId(habit.getStudentId());
        if (existing.isPresent()) {
            HabitProfile existingHabit = existing.get();
            if (habit.getStudyHoursPerDay() != null) {
                existingHabit.setStudyHoursPerDay(habit.getStudyHoursPerDay());
            }
            if (habit.getSleepSchedule() != null) {
                existingHabit.setSleepSchedule(habit.getSleepSchedule());
            }
            if (habit.getCleanlinessLevel() != null) {
                existingHabit.setCleanlinessLevel(habit.getCleanlinessLevel());
            }
            if (habit.getNoiseTolerance() != null) {
                existingHabit.setNoiseTolerance(habit.getNoiseTolerance());
            }
            if (habit.getSocialPreference() != null) {
                existingHabit.setSocialPreference(habit.getSocialPreference());
            }
            return habitRepo.save(existingHabit);
        }
        
        return habitRepo.save(habit);
    }

    @Override
    public Optional<HabitProfile> getHabitById(Long id) {
        return habitRepo.findById(id);
    }

    @Override
    public Optional<HabitProfile> getHabitByStudent(Long studentId) {
        return habitRepo.findByStudentId(studentId);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitRepo.findAll();
    }
}
