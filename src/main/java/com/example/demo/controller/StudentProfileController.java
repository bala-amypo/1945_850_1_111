package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profile", description = "Student profile management")
public class StudentProfileController {
    
    private final StudentProfileService studentService;
    
    public StudentProfileController(StudentProfileService studentService) {
        this.studentService = studentService;
    }
    
    @PostMapping
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<StudentProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(studentService.updateStudentStatus(id, active));
    }
    
    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> lookup(@PathVariable String studentId) {
        Optional<StudentProfile> student = studentService.findByStudentId(studentId);
        return student.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
}