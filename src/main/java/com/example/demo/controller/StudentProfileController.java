package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentService;

    public StudentProfileController() {}

    public StudentProfileController(StudentProfileService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentProfileDto> create(@RequestBody StudentProfile profile) {
        StudentProfile created = studentService.createStudent(profile);
        return ResponseEntity.ok(mapToDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDto> getById(@PathVariable Long id) {
        StudentProfile student = studentService.getStudentById(id);
        return ResponseEntity.ok(mapToDto(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    private StudentProfileDto mapToDto(StudentProfile profile) {
        StudentProfileDto dto = new StudentProfileDto();
        dto.setId(profile.getId());
        dto.setStudentId(profile.getStudentId());
        dto.setEmail(profile.getEmail());
        dto.setFullName(profile.getFullName());
        dto.setAge(profile.getAge());
        dto.setCourse(profile.getCourse());
        dto.setYearOfStudy(profile.getYearOfStudy());
        dto.setGender(profile.getGender());
        dto.setRoomTypePreference(profile.getRoomTypePreference());
        dto.setActive(profile.getActive());
        return dto;
    }
}
