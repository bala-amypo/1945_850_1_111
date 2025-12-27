package com.example.demo.service;

import com.example.demo.model.HabitProfile;

import java.util.List;
import java.util.Optional;

public interface HabitProfileService {

    HabitProfile createOrUpdateHabit(HabitProfile habitProfile);

    Optional<HabitProfile> getHabitById(long id);

    Optional<HabitProfile> getHabitByStudent(long studentId);

    List<HabitProfile> getAllHabitProfiles();
}
