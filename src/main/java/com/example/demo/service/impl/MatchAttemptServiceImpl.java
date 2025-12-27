@Override
public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
    if (attempt.getResultScoreId() != null) {
        attempt.setStatus(MatchAttemptRecord.Status.MATCHED);
    } else {
        attempt.setStatus(MatchAttemptRecord.Status.PENDING_REVIEW);
    }
    return matchRepo.save(attempt);
}

@Override
public MatchAttemptRecord updateAttemptStatus(Long id, String status) {
    return updateAttemptStatus(id, MatchAttemptRecord.Status.valueOf(status));
}

@Override
public MatchAttemptRecord updateAttemptStatus(Long id, MatchAttemptRecord.Status status) {
    MatchAttemptRecord a = matchRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Match attempt not found"));
    a.setStatus(status);
    return matchRepo.save(a);
}
