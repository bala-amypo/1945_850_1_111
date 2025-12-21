package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService studentService;

    public StudentProfileController(StudentProfileService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentProfile create(@RequestBody StudentProfileDto dto) {
        return studentService.createStudent(dto);
    }

    @PutMapping("/{id}")
    public StudentProfile update(@PathVariable Long id, @RequestBody StudentProfileDto dto) {
        return studentService.updateStudent(id, dto);
    }

    @GetMapping("/{id}")
    public StudentProfile get(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentProfile> list() {
        return studentService.getAllStudents();
    }
}
