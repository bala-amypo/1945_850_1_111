package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId;

    @Column(unique = true)
    private String email;

    private String fullName;
    private boolean active = true;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public UserAccount getUserAccount() { return userAccount; }
    public void setUserAccount(UserAccount userAccount) { this.userAccount = userAccount; }
}
