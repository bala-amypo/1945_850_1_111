package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitProfileController {

    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitProfile> createHabit(@RequestBody HabitProfile habitProfile) {
        return ResponseEntity.ok(habitService.saveHabitProfile(habitProfile));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<HabitProfile>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(habitService.getHabitByStudent(studentId));
    }
}
