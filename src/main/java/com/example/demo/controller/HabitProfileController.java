package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    @Autowired
    private HabitProfileService habitService;

    public HabitProfileController() {}

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitProfileDto> create(@RequestBody HabitProfile habit) {
        HabitProfile created = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(mapToDto(created));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<HabitProfileDto> getByStudent(@PathVariable Long studentId) {
        Optional<HabitProfile> habit = habitService.getHabitByStudent(studentId);
        if (habit.isPresent()) {
            return ResponseEntity.ok(mapToDto(habit.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private HabitProfileDto mapToDto(HabitProfile habit) {
        HabitProfileDto dto = new HabitProfileDto();
        dto.setId(habit.getId());
        dto.setStudentId(habit.getStudentId());
        dto.setSmoking(habit.getSmoking());
        dto.setDrinking(habit.getDrinking());
        dto.setSleepSchedule(habit.getSleepSchedule());
        dto.setCleanlinessLevel(habit.getCleanlinessLevel());
        dto.setNoiseTolerance(habit.getNoiseTolerance());
        dto.setSocialPreference(habit.getSocialPreference());
        dto.setStudyHoursPerDay(habit.getStudyHoursPerDay());
        return dto;
    }
}
