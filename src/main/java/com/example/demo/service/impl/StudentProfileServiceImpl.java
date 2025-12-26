// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.StudentProfile;
// import com.example.demo.repository.StudentProfileRepository;
// import com.example.demo.service.StudentProfileService;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class StudentProfileServiceImpl implements StudentProfileService {
    
//     private final StudentProfileRepository studentProfileRepository;

//     public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
//         this.studentProfileRepository = studentProfileRepository;
//     }
    
//     @Override
//     public StudentProfile createStudent(StudentProfile student) {
//         if (studentProfileRepository.findByStudentId(student.getStudentId()).isPresent()) {
//             throw new IllegalArgumentException("studentId exists");
//         }
//         if (studentProfileRepository.findByEmail(student.getEmail()).isPresent()) {
//             throw new IllegalArgumentException("email exists");
//         }
//         return studentProfileRepository.save(student);
//     }
    
//     @Override
//     public StudentProfile getStudentById(Long id) {
//         return studentProfileRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
//     }
    
//     @Override
//     public StudentProfile updateStudentStatus(Long id, Boolean active) {
//         StudentProfile student = getStudentById(id);
//         student.setActive(active);
//         return studentProfileRepository.save(student);
//     }
    
//     @Override
//     public List<StudentProfile> getAllStudents() {
//         return studentProfileRepository.findAll();
//     }
    
//     @Override
//     public Optional<StudentProfile> findByStudentId(String studentId) {
//         return studentProfileRepository.findByStudentId(studentId);
//     }
// }