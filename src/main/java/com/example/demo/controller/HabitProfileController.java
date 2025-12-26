package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profiles", description = "Habit profile management")
public class HabitProfileController {
    
    private final HabitProfileService habitProfileService;
    
    public HabitProfileController(HabitProfileService habitProfileService) {
        this.habitProfileService = habitProfileService;
    }
    
    @PostMapping
    @Operation(summary = "Create or update habit profile")
    public ResponseEntity<HabitProfile> createOrUpdateHabit(@RequestBody HabitProfile habit) {
        HabitProfile created = habitProfileService.createOrUpdateHabit(habit);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get habit profile for student")
    public ResponseEntity<HabitProfile> getByStudent(@PathVariable Long studentId) {
        HabitProfile habit = habitProfileService.getHabitByStudent(studentId);
        return ResponseEntity.ok(habit);
    }
}