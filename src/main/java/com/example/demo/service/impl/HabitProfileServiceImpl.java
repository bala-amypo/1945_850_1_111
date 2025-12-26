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

    private final HabitProfileRepository habitRepo;
    private final StudentProfileRepository studentRepo;

    public HabitProfileServiceImpl(HabitProfileRepository habitRepo, StudentProfileRepository studentRepo) {
        this.habitRepo = habitRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours must be positive");
        }
        
        // Ensure student exists
        if (!studentRepo.findById(habit.getStudentId()).isPresent()) {
            throw new ResourceNotFoundException("Student not found");
        }
        
        Optional<HabitProfile> existing = habitRepo.findByStudentId(habit.getStudentId());
        if (existing.isPresent()) {
            HabitProfile updated = existing.get();
            updated.setStudyHoursPerDay(habit.getStudyHoursPerDay());
            updated.setSleepSchedule(habit.getSleepSchedule());
            updated.setCleanlinessLevel(habit.getCleanlinessLevel());
            updated.setNoiseTolerance(habit.getNoiseTolerance());
            updated.setSocialPreference(habit.getSocialPreference());
            return habitRepo.save(updated);
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
