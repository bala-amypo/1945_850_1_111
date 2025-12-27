package com.example.demo.repository;

import com.example.demo.model.HabitProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitProfileRepository extends JpaRepository<HabitProfile, Long> {

    Optional<HabitProfile> findByStudentId(Long studentId);

    // 🔥 REQUIRED BY TESTS
    @Query("SELECT DISTINCT h.studentId AS studentId FROM HabitProfile h")
    List<StudentIdView> findDistinctStudents();
}
