package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import java.util.List;

public interface HabitProfileService {

    HabitProfile saveHabitProfile(HabitProfile habitProfile);

    HabitProfile updateHabitProfile(Long id, HabitProfile habitProfile);

    HabitProfile getHabitProfileById(Long id);

    List<HabitProfile> getAllHabitProfiles();

    List<HabitProfile> getHabitByStudent(Long studentId);

    void deleteHabitProfile(Long id);
}
