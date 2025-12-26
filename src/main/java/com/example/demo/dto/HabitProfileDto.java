package com.example.demo.dto;

import com.example.demo.model.HabitProfile.*;
import lombok.Data;

@Data
public class HabitProfileDto {
    private Long studentId;
    private Integer studyHoursPerDay;
    private SleepSchedule sleepSchedule;
    private CleanlinessLevel cleanlinessLevel;
    private NoiseTolerance noiseTolerance;
    private SocialPreference socialPreference;
}
