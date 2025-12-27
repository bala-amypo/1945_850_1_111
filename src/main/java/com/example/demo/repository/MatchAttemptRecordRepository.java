package com.example.demo.repository;

import com.example.demo.model.MatchAttemptRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchAttemptRecordRepository
        extends JpaRepository<MatchAttemptRecord, Long> {

    List<MatchAttemptRecord> findByInitiatorStudentIdOrCandidateStudentId(Long id1, Long id2);
    @Query("""
        select distinct m.initiatorStudentId as studentId
        from MatchAttemptRecord m
    """)
    List<StudentIdView> findDistinctStudents();
}
