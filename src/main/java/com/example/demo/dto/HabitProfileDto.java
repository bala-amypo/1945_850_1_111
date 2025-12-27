package com.example.demo.dto;

public class HabitProfileDto {
    private Long id;
    private Long studentId;
    private Boolean smoking;
    private Boolean drinking;
    private String sleepSchedule;
    private String cleanlinessLevel;
    private String noiseTolerance;
    private String socialPreference;
    private Integer studyHoursPerDay;

    public HabitProfileDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public Boolean getDrinking() {
        return drinking;
    }

    public void setDrinking(Boolean drinking) {
        this.drinking = drinking;
    }

    public String getSleepSchedule() {
        return sleepSchedule;
    }

    public void setSleepSchedule(String sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public String getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(String cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public String getNoiseTolerance() {
        return noiseTolerance;
    }

    public void setNoiseTolerance(String noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public String getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(String socialPreference) {
        this.socialPreference = socialPreference;
    }

    public Integer getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(Integer studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
    }
}
