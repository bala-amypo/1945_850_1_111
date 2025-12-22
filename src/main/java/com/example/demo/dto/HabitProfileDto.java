package com.example.demo.dto;

public class HabitProfileDto {

    private Long id;
    private String sleepSchedule;
    private int studyHoursPerDay;
    private String cleanlinessLevel;
    private String noiseTolerance;
    private String socialPreference;

    public HabitProfileDto() {
    }

    public HabitProfileDto(Long id, String sleepSchedule,
                           int studyHoursPerDay,
                           String cleanlinessLevel,
                           String noiseTolerance,
                           String socialPreference) {
        this.id = id;
        this.sleepSchedule = sleepSchedule;
        this.studyHoursPerDay = studyHoursPerDay;
        this.cleanlinessLevel = cleanlinessLevel;
        this.noiseTolerance = noiseTolerance;
        this.socialPreference = socialPreference;
    }

    public Long getId() {
        return id;
    }

    public String getSleepSchedule() {
        return sleepSchedule;
    }

    public int getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public String getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public String getNoiseTolerance() {
        return noiseTolerance;
    }

    public String getSocialPreference() {
        return socialPreference;
    }
}
