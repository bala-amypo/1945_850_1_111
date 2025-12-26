package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;

public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    public ResponseEntity<StudentProfile> create(StudentProfile s) {
        return ResponseEntity.ok(service.createStudent(s));
    }
}
