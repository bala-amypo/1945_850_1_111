@Entity
@Table(name = "matchattemptrecord")
public class MatchAttemptRecord {

    public enum Status {
        PENDING_REVIEW,
        MATCHED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING_REVIEW;

    // ---------- getters/setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInitiatorStudentId() { return initiatorStudentId; }
    public void setInitiatorStudentId(Long initiatorStudentId) { this.initiatorStudentId = initiatorStudentId; }

    public Long getCandidateStudentId() { return candidateStudentId; }
    public void setCandidateStudentId(Long candidateStudentId) { this.candidateStudentId = candidateStudentId; }

    public Long getResultScoreId() { return resultScoreId; }
    public void setResultScoreId(Long resultScoreId) { this.resultScoreId = resultScoreId; }

    // ✅ TESTS EXPECT ENUM, NOT STRING
    public Status getStatus() {
        return status;
    }

    // used by services (enum)
    public void setStatus(Status status) {
        this.status = status;
    }

    // used by tests/controllers (string)
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
}
