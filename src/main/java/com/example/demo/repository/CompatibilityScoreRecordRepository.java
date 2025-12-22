package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompatibilityScoreRecordRepository extends JpaRepository<CompatibilityScoreRecord, Long> {
}
