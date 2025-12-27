package com.example.demo.repository;

import com.example.demo.model.HabitProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitProfileRepository extends JpaRepository<HabitProfile, Long> {

    Optional<HabitProfile> findByStudentId(Long studentId);

    // ✅ THIS IS WHAT MAKES getStudentId() WORK
    @Query("select distinct h.studentId as studentId from HabitProfile h")
    List<StudentIdView> findDistinctStudents();
}
