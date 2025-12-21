package com.example.demo.service.impl;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;
    private final StudentProfileRepository studentProfileRepository;

    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository,
                                   StudentProfileRepository studentProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfileDto dto) {
        StudentProfile student = studentProfileRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (dto.getStudyHoursPerDay() != null && dto.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours must be >= 0");
        }

        HabitProfile habit = habitProfileRepository.findByStudentId(student.getId())
                .orElse(new HabitProfile());

        habit.setStudentId(student.getId());
        habit.setSleepSchedule(dto.getSleepSchedule());
        habit.setStudyHoursPerDay(dto.getStudyHoursPerDay());
        habit.setCleanlinessLevel(dto.getCleanlinessLevel());
        habit.setNoiseTolerance(dto.getNoiseTolerance());
        habit.setSocialPreference(dto.getSocialPreference());

        return habitProfileRepository.save(habit);
    }

    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    public List<HabitProfile> getAllHabits() {
        return habitProfileRepository.findAll();
    }
}
