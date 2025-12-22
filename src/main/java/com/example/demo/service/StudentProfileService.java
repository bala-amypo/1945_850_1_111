package com.example.demo.service;

import com.example.demo.dto.StudentProfileDto;

import java.util.List;
import java.util.Optional;

public interface StudentProfileService {

    StudentProfileDto createProfile(StudentProfileDto dto);

    StudentProfileDto updateProfile(Long id, StudentProfileDto dto);

    Optional<StudentProfileDto> getProfileById(Long id);

    List<StudentProfileDto> getAllProfiles();

    void deleteProfile(Long id);
}
