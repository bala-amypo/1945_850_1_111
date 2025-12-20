package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "habit_profiles",
    uniqueConstraints = @UniqueConstraint(columnNames = "student_id")
)
public class HabitProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, unique = true)
    private StudentProfile student;

    private boolean smoking;
    private boolean drinking;

    private String sleepTime;
    private String wakeTime;

    private int cleanlinessLevel; // 1–5
    private int noisePreference;  // 1–5

    private String studyStyle;
    private String socialPreference;
    private String visitorsFrequency;

    public HabitProfile() {}

    public Long getId() {
        return id;
    }

    public StudentProfile getStudent() {
        return student;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isDrinking() {
        return drinking;
    }

    public void setDrinking(boolean drinking) {
        this.drinking = drinking;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public int getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(int cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public int getNoisePreference() {
        return noisePreference;
    }

    public void setNoisePreference(int noisePreference) {
        this.noisePreference = noisePreference;
    }

    public String getStudyStyle() {
        return studyStyle;
    }

    public void setStudyStyle(String studyStyle) {
        this.studyStyle = studyStyle;
    }

    public String getSocialPreference() {
        return socialPreference;
    }

    public void setSocialPreference(String socialPreference) {
        this.socialPreference = socialPreference;
    }

    public String getVisitorsFrequency() {
        return visitorsFrequency;
    }

    public void setVisitorsFrequency(String visitorsFrequency) {
        this.visitorsFrequency = visitorsFrequency;
    }
}
