package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student Profiles", description = "Student profile management")
@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService studentService;

    public StudentProfileController(StudentProfileService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile student) {
        return ResponseEntity.ok(studentService.create(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
