package com.example.demo.service.impl;

import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private StudentProfileRepository studentRepo;

    @Autowired
    public StudentProfileServiceImpl(StudentProfileRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile profile) {
        if (studentRepo.findByStudentId(profile.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("Error: studentId exists already");
        }
        if (studentRepo.findByEmail(profile.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Error: email already exists");
        }
        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile updateStudentStatus(Long id, Boolean active) {
        StudentProfile student = getStudentById(id);
        student.setActive(active);
        return studentRepo.save(student);
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<StudentProfile> findByStudentId(String studentId) {
        return studentRepo.findByStudentId(studentId);
    }
}
