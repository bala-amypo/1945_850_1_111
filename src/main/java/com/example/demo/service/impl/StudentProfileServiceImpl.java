package com.example.demo.service.impl;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public StudentProfile createStudent(StudentProfileDto dto) {
        if (dto.getYearLevel() != null && dto.getYearLevel() <= 0) {
            throw new IllegalArgumentException("year must be > 0");
        }

        StudentProfile s = new StudentProfile();
        s.setStudentId(dto.getStudentId());
        s.setFullName(dto.getFullName());
        s.setEmail(dto.getEmail());
        s.setDepartment(dto.getDepartment());
        s.setYearLevel(dto.getYearLevel());
        s.setActive(dto.getActive() == null || dto.getActive());

        return studentProfileRepository.save(s);
    }

    @Override
    public StudentProfile updateStudent(Long id, StudentProfileDto dto) {
        StudentProfile s = getStudentById(id);
        if (dto.getYearLevel() != null && dto.getYearLevel() <= 0) {
            throw new IllegalArgumentException("year must be > 0");
        }
        s.setFullName(dto.getFullName());
        s.setDepartment(dto.getDepartment());
        s.setYearLevel(dto.getYearLevel());
        return studentProfileRepository.save(s);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public void updateStudentStatus(Long id, boolean active) {
        StudentProfile s = getStudentById(id);
        s.setActive(active);
        studentProfileRepository.save(s);
    }
}
