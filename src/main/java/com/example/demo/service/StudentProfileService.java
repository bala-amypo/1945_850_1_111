package com.example.demo.service;

import com.example.demo.model.StudentProfile;
import java.util.List;
import java.util.Optional;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfile student);  // ✅ FIXED: createStudent
    StudentProfile getStudentById(Long id);
    List<StudentProfile> getAllStudents();
    StudentProfile updateStudentStatus(Long id, boolean active);
    Optional<StudentProfile> findByStudentId(String studentId);
    Optional<StudentProfile> findByEmail(String email);
}
