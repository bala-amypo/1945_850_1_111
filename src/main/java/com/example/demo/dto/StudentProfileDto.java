package com.example.demo.dto;

public class StudentProfileDto {
    private Long id;
    private String studentId;
    private String email;
    private String fullName;
    private Integer age;
    private String course;
    private Integer yearOfStudy;
    private String gender;
    private String roomTypePreference;
    private Boolean active;

    public StudentProfileDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomTypePreference() {
        return roomTypePreference;
    }

    public void setRoomTypePreference(String roomTypePreference) {
        this.roomTypePreference = roomTypePreference;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
