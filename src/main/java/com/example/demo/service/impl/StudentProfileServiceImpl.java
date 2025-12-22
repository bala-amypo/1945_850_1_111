package com.example.demo.service.impl;

import com.example.demo.dto.StudentProfileDto;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfileDto createProfile(StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile();

        profile.setFullName(dto.getFullName());
        profile.setAge(dto.getAge());
        profile.setDepartment(dto.getDepartment());
        profile.setYearLevel(dto.getYearLevel());
        profile.setActive(true);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());

        StudentProfile saved = studentProfileRepository.save(profile);
        return mapToDto(saved);
    }

    @Override
    public StudentProfileDto updateProfile(Long id, StudentProfileDto dto) {
        StudentProfile profile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        profile.setFullName(dto.getFullName());
        profile.setAge(dto.getAge());
        profile.setDepartment(dto.getDepartment());
        profile.setYearLevel(dto.getYearLevel());
        profile.setUpdatedAt(LocalDateTime.now());

        StudentProfile saved = studentProfileRepository.save(profile);
        return mapToDto(saved);
    }

    @Override
    public Optional<StudentProfileDto> getProfileById(Long id) {
        return studentProfileRepository.findById(id)
                .map(this::mapToDto);
    }

    @Override
    public List<StudentProfileDto> getAllProfiles() {
        return studentProfileRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProfile(Long id) {
        studentProfileRepository.deleteById(id);
    }

    private StudentProfileDto mapToDto(StudentProfile profile) {
        StudentProfileDto dto = new StudentProfileDto();
        dto.setId(profile.getId());
        dto.setFullName(profile.getFullName());
        dto.setAge(profile.getAge());
        dto.setDepartment(profile.getDepartment());
        dto.setYearLevel(profile.getYearLevel());
        dto.setActive(profile.getActive());
        dto.setCreatedAt(profile.getCreatedAt());
        dto.setUpdatedAt(profile.getUpdatedAt());
        return dto;
    }
}
