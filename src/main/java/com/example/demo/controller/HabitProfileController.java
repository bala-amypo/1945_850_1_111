package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habits")
public class HabitProfileController {

    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdateHabit(@RequestBody HabitProfile h) {
        return ResponseEntity.ok(service.createOrUpdateHabit(h));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HabitProfile>> getHabitById(@PathVariable long id) {
        return ResponseEntity.ok(service.getHabitById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Optional<HabitProfile>> getByStudent(@PathVariable long studentId) {
        return ResponseEntity.ok(service.getHabitByStudent(studentId));
    }

    @GetMapping
    public ResponseEntity<List<HabitProfile>> getAll() {
        return ResponseEntity.ok(service.getAllHabitProfiles());
    }
}
