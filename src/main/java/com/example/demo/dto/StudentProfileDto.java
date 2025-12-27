package com.example.demo.dto;

public class StudentProfileDto {
    private String studentId;
    private String fullName;
    private String email;
    private String department;
    private Integer yearLevel;
    private Boolean active;
    
    // Constructors
    public StudentProfileDto() {}
    
    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public Integer getYearLevel() { return yearLevel; }
    public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}