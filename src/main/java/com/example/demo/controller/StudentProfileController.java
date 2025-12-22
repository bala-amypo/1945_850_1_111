package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @PostMapping
    public ResponseEntity<StudentProfileDto> createProfile(@RequestBody StudentProfileDto dto) {
        StudentProfileDto created = studentProfileService.createProfile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentProfileDto> updateProfile(
            @PathVariable Long id,
            @RequestBody StudentProfileDto dto) {

        StudentProfileDto updated = studentProfileService.updateProfile(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDto> getProfile(@PathVariable Long id) {
        return studentProfileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(studentProfileService.getAllProfiles());
    }
}
