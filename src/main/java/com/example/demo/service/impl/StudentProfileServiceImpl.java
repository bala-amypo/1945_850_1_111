// src/main/java/com/example/demo/service/impl/StudentProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile student) {
        if (studentRepo.findByStudentId(student.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("studentId exists");
        }
        if (studentRepo.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email exists");
        }
        return studentRepo.save(student);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, boolean active) {
        StudentProfile s = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        s.setActive(active);
        return studentRepo.save(s);
    }

    @Override
    public Optional<StudentProfile> findByStudentId(String studentId) {
        return studentRepo.findByStudentId(studentId);
    }

    @Override
    public Optional<StudentProfile> findByEmail(String email) {
        return studentRepo.findByEmail(email);
    }
}
