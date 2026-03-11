package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Flat representation of one milestone entry for storage and reconstruction.
 */
public final class FlatMilestoneEntry {
    private final StudentId studentId;
    private final AssignmentId assignmentId;
    private final MilestoneRecord milestoneRecord;

    public FlatMilestoneEntry(StudentId studentId, AssignmentId assignmentId, MilestoneRecord milestoneRecord) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);
        requireNonNull(milestoneRecord);

        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.milestoneRecord = milestoneRecord;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    public MilestoneRecord getMilestoneRecord() {
        return milestoneRecord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FlatMilestoneEntry)) {
            return false;
        }
        FlatMilestoneEntry otherEntry = (FlatMilestoneEntry) other;
        return studentId.equals(otherEntry.studentId)
                && assignmentId.equals(otherEntry.assignmentId)
                && milestoneRecord.equals(otherEntry.milestoneRecord);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + assignmentId.hashCode();
        result = 31 * result + milestoneRecord.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FlatMilestoneEntry{studentId=" + studentId
                + ", assignmentId=" + assignmentId
                + ", milestoneRecord=" + milestoneRecord + "}";
    }
}
