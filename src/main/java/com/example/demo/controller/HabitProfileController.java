package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Habit Profiles", description = "Habit profile management")
@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdate(@RequestBody HabitProfile habit) {
        return ResponseEntity.ok(habitService.createOrUpdateHabit(habit));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Long studentId) {
        Optional<HabitProfile> habit = habitService.getHabitByStudent(studentId);
        return habit.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
}
