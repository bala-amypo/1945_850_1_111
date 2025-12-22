package com.example.demo.service;

import com.example.demo.dto.HabitProfileDto;
import java.util.Optional;

public interface HabitProfileService {
    HabitProfileDto createOrUpdateHabit(Long studentId, HabitProfileDto dto);
    Optional<HabitProfileDto> getHabitByStudentId(Long studentId);
}
