package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habits")
public class HabitProfileController {

    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    // CREATE or UPDATE habit
    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdateHabit(
            @RequestBody HabitProfile habitProfile) {

        return ResponseEntity.ok(
                habitService.createOrUpdateHabit(habitProfile)
        );
    }

    // GET habit by ID
    @GetMapping("/{id}")
    public ResponseEntity<HabitProfile> getHabitById(@PathVariable long id) {

        return ResponseEntity.ok(
                habitService.getHabitById(id)
        );
    }
}
