package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Immutable milestone record for one student-assignment pair.
 */
public final class MilestoneRecord {
    public static final String MESSAGE_COMPLETED_AT_REQUIRES_COMPLETED_STATUS =
            "completedAt can only be non-empty when status is COMPLETED.";

    private final MilestoneStatus status;
    private final CompletedAt completedAt;

    public MilestoneRecord(MilestoneStatus status, CompletedAt completedAt) {
        requireNonNull(status);
        requireNonNull(completedAt);

        if (!completedAt.isEmpty() && status != MilestoneStatus.COMPLETED) {
            throw new IllegalArgumentException(MESSAGE_COMPLETED_AT_REQUIRES_COMPLETED_STATUS);
        }

        this.status = status;
        this.completedAt = completedAt;
    }

    public MilestoneStatus getStatus() {
        return status;
    }

    public CompletedAt getCompletedAt() {
        return completedAt;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MilestoneRecord)) {
            return false;
        }
        MilestoneRecord otherRecord = (MilestoneRecord) other;
        return status.equals(otherRecord.status)
                && completedAt.equals(otherRecord.completedAt);
    }

    @Override
    public int hashCode() {
        return status.hashCode() * 31 + completedAt.hashCode();
    }

    @Override
    public String toString() {
        return "MilestoneRecord{status=" + status + ", completedAt=" + completedAt + "}";
    }
}
