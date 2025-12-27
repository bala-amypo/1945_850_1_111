package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profiles")
public class HabitProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false, unique = true)
    private Long studentId;

    @Column(name = "smoking")
    private Boolean smoking = false;

    @Column(name = "drinking")
    private Boolean drinking = false;

    @Column(name = "sleep_schedule")
    private String sleepSchedule;

    @Column(name = "cleanliness_level")
    private String cleanlinessLevel;

    @Column(name = "noise_tolerance")
    private String noiseTolerance;

    @Column(name = "social_preference")
    private String socialPreference;

    @Column(name = "study_hours_per_day")
    private Integer studyHoursPerDay;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum SleepSchedule {
        EARLY, REGULAR, LATE
    }

    public enum CleanlinessLevel {
        LOW, MEDIUM, HIGH
    }

    public enum NoiseTolerance {
        LOW, MEDIUM, HIGH
    }

    public enum SocialPreference {
        INTROVERT, BALANCED, EXTROVERT
    }

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

    public void setSleepSchedule(SleepSchedule sleepSchedule) {
        this.sleepSchedule = sleepSchedule != null ? sleepSchedule.name() : null;
    }

    public String getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(String cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public void setCleanlinessLevel(CleanlinessLevel cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel != null ? cleanlinessLevel.name() : null;
    }

    public String getNoiseTolerance() {
        return noiseTolerance;
    }

    public void setNoiseTolerance(String noiseTolerance) {
        this.noiseTolerance = noiseTolerance;
    }

    public void setNoiseTolerance(NoiseTolerance noiseTolerance) {
        this.noiseTolerance = noiseTolerance != null ? noiseTolerance.name() : null;
    }

    public String getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(String socialPreference) {
        this.socialPreference = socialPreference;
    }

    public void setSocialPreference(SocialPreference socialPreference) {
        this.socialPreference = socialPreference != null ? socialPreference.name() : null;
    }

    public Integer getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(Integer studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
