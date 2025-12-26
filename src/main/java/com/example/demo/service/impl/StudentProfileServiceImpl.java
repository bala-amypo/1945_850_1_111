package com.example.demo.service.impl;

@Override
public StudentProfile createStudent(StudentProfile student) {  // ✅ FIXED: createStudent
    if (studentRepo.findByStudentId(student.getStudentId()).isPresent()) {
        throw new IllegalArgumentException("studentId exists");
    }
    if (student.getEmail() != null && studentRepo.findByEmail(student.getEmail()).isPresent()) {
        throw new IllegalArgumentException("email exists");
    }
    return studentRepo.save(student);
}
