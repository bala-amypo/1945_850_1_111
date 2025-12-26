package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "studentprofile")
@Data
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId;

    private String email;
    private String fullName;

    private boolean active = true;

    @OneToOne
    @JoinColumn(name = "useraccount_id")
    private UserAccount userAccount;
}
