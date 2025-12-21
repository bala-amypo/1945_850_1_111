package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @PostMapping("/{studentId}")
    public HabitProfile createOrUpdate(@PathVariable Long studentId,
                                       @RequestBody HabitProfileDto dto) {
        dto.setStudentId(studentId);
        return habitService.createOrUpdateHabit(dto);
    }

    @GetMapping("/{studentId}")
    public HabitProfile getForStudent(@PathVariable Long studentId) {
        return habitService.getHabitByStudent(studentId);
    }

    @GetMapping
    public List<HabitProfile> listAll() {
        return habitService.getAllHabits();
    }
}
