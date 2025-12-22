package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HabitProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private StudentProfile student;

    private String sleepSchedule;
    private Integer studyHoursPerDay;
    private String cleanlinessLevel;
    private String noiseTolerance;
    private String socialPreference;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public StudentProfile getStudent() {
        return student;
    }
    
    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public String getSleepSchedule() {
        return sleepSchedule;
    }
    
    public void setSleepSchedule(String sleepSchedule) {
        this.sleepSchedule = sleepSchedule;
    }

    public Integer getStudyHoursPerDay() {
        return studyHoursPerDay;
    }
    
    public void setStudyHoursPerDay(Integer studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
