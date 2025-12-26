package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;

public class HabitProfileController {

    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    public ResponseEntity<HabitProfile> getByStudent(Long studentId) {
        return ResponseEntity.ok(service.getHabitByStudent(studentId));
    }
}
