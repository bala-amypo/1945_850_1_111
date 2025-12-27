package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;

    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    @Override
    public HabitProfile saveHabitProfile(HabitProfile h) {
        h.setUpdatedAt(LocalDateTime.now());
        return habitProfileRepository.save(h);
    }

    @Override
    public HabitProfile updateHabitProfile(Long id, HabitProfile h) {
        HabitProfile existing = habitProfileRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setStudentId(h.getStudentId());
            existing.setStudyHoursPerDay(h.getStudyHoursPerDay());
            existing.setHabitName(h.getHabitName());
            existing.setFrequency(h.getFrequency());
            existing.setUpdatedAt(LocalDateTime.now());
            return habitProfileRepository.save(existing);
        }
        return null;
    }

    @Override
    public HabitProfile getHabitProfileById(Long id) {
        return habitProfileRepository.findById(id).orElse(null);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitProfileRepository.findAll();
    }

    @Override
    public List<HabitProfile> getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId);
    }

    @Override
    public void deleteHabitProfile(Long id) {
        habitProfileRepository.deleteById(id);
    }
}
