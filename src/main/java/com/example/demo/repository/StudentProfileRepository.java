package com.example.demo.repository;

import com.example.demo.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    Optional<StudentProfile> findByStudentId(String studentId);

    Optional<StudentProfile> findByEmail(String email);

    List<StudentProfile> findByIdIn(List<Long> ids);

    // ✅ REQUIRED BY TESTS
    @Query("select distinct s.studentId as studentId from StudentProfile s")
    List<StudentIdView> findDistinctStudents();
}
