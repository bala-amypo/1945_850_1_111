package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.service.HabitProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    @Autowired
    private HabitProfileService habitProfileService;

    @PostMapping("/{studentId}")
    public ResponseEntity<HabitProfileDto> createOrUpdateHabit(
            @PathVariable Long studentId,
            @RequestBody HabitProfileDto dto) {

        HabitProfileDto saved = habitProfileService.createOrUpdateHabit(studentId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<HabitProfileDto> getHabit(@PathVariable Long studentId) {
        return habitProfileService.getHabitByStudentId(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

//sleepschedule,cleanlinesslevel,noise tolerance, socialpreference