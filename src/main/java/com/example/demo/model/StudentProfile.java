package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class StudentProfile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private String fullname;
    private String email;
    private String department;
    private int yearLevel;
    private Boolean active;
    private LocalDateTime createdAt;
     
  public StudentProfile(Long id, String studentId,String email,String department,int yearLevel,Boolean active,LocalDateTime createdAt) {
        this.id = id;
       this.studentid=studentid;
       this.fullname=fullname;
       this.email=email;
       this.department=department;
       this.yearLevel=yearLevel;
       this.active=active;
       this.createdAt=createdAt;
    }
    
    
}