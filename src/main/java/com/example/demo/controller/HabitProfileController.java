package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profile", description = "Habit profile management")
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
    public ResponseEntity<HabitProfile> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(habitService.getHabitByStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HabitProfile> getById(@PathVariable Long id) {
        Optional<HabitProfile> habit = habitService.getHabitById(id);
        return habit.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<HabitProfile>> getAll() {
        return ResponseEntity.ok(habitService.getAllHabitProfiles());
    }
}