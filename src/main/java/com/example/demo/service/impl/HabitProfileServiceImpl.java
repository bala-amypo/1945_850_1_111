package com.example.demo.service.impl;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    @Autowired
    private HabitProfileRepository habitProfileRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public HabitProfileDto createOrUpdateHabit(Long studentId, HabitProfileDto dto) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        HabitProfile habit = habitProfileRepository.findByStudentId(studentId)
                .orElse(new HabitProfile());

        habit.setStudent(student);
        habit.setSleepSchedule(dto.getSleepSchedule());
        habit.setStudyHoursPerDay(dto.getStudyHoursPerDay());
        habit.setCleanlinessLevel(dto.getCleanlinessLevel());
        habit.setNoiseTolerance(dto.getNoiseTolerance());
        habit.setSocialPreference(dto.getSocialPreference());
        habit.setUpdatedAt(LocalDateTime.now());

        HabitProfile saved = habitProfileRepository.save(habit);
        return mapToDto(saved);
    }

    @Override
    public Optional<HabitProfileDto> getHabitByStudentId(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .map(this::mapToDto);
    }

    private HabitProfileDto mapToDto(HabitProfile habit) {
        return new HabitProfileDto(
        );
    }
}
