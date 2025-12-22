package com.example.demo.dto;

import java.time.LocalDateTime;

public class StudentProfileDto {

    private Long id;
    private String fullName;
    private int age;
    private String department;
    private int yearLevel;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentProfileDto() {
    }

    public StudentProfileDto(Long id,
                             String fullName,
                             int age,
                             String department,
                             int yearLevel,
                             boolean active,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.yearLevel = yearLevel;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
