package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
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
    public HabitProfile createOrUpdateHabit(@RequestBody HabitProfile h) {
        return service.createOrUpdateHabit(h);
    }

    @GetMapping("/{id}")
    public Optional<HabitProfile> getHabitById(@PathVariable long id) {
        return service.getHabitById(id);
    }

    @GetMapping("/student/{studentId}")
    public Optional<HabitProfile> getByStudent(@PathVariable long studentId) {
        return service.getHabitByStudent(studentId);
    }

    @GetMapping
    public List<HabitProfile> getAll() {
        return service.getAllHabitProfiles();
    }
}
