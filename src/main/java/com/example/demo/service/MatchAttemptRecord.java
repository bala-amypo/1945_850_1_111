package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;

public interface MatchAttemptRecordService {

    MatchAttemptRecord createAttempt(Long initiatorId, Long candidateId);

    MatchAttemptRecord approveAttempt(Long id);

    MatchAttemptRecord rejectAttempt(Long id);
}
