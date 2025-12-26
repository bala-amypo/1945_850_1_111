package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profile")
public class HabitProfile {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    private Integer studyHoursPerDay;
    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;
    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevel;
    @Enumerated(EnumType.STRING)
    private NoiseTolerance noiseTolerance;
    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreference;

    private LocalDateTime updatedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Integer getStudyHoursPerDay() { return studyHoursPerDay; }
    public void setStudyHoursPerDay(Integer studyHoursPerDay) { this.studyHoursPerDay = studyHoursPerDay; }
    public SleepSchedule getSleepSchedule() { return sleepSchedule; }
    public void setSleepSchedule(SleepSchedule sleepSchedule) { this.sleepSchedule = sleepSchedule; }
    public CleanlinessLevel getCleanlinessLevel() { return cleanlinessLevel; }
    public void setCleanlinessLevel(CleanlinessLevel cleanlinessLevel) { this.cleanlinessLevel = cleanlinessLevel; }
    public NoiseTolerance getNoiseTolerance() { return noiseTolerance; }
    public void setNoiseTolerance(NoiseTolerance noiseTolerance) { this.noiseTolerance = noiseTolerance; }
    public SocialPreference getSocialPreference() { return socialPreference; }
    public void setSocialPreference(SocialPreference socialPreference) { this.socialPreference = socialPreference; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
