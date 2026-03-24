package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.person.StudentId;

/**
 * Represents one fully resolved milestone for display purposes.
 *
 * <p>A resolved milestone combines:
 * <ul>
 *   <li>the student identifier</li>
 *   <li>the assignment metadata</li>
 *   <li>the stored raw milestone record</li>
 *   <li>the computed display status</li>
 * </ul>
 *
 * <p>This class is read-only and should not be persisted directly.
 */
public final class ResolvedMilestone {

    private final StudentId studentId;
    private final Assignment assignment;
    private final MilestoneRecord rawRecord;
    private final ResolvedMilestoneStatus resolvedStatus;

    /**
     * Constructs a {@code ResolvedMilestone}.
     *
     * @param studentId The student this milestone belongs to.
     * @param assignment The assignment this milestone corresponds to.
     * @param rawRecord The stored milestone record, or a default virtual record if absent in storage.
     * @param resolvedStatus The computed display status.
     */
    public ResolvedMilestone(StudentId studentId, Assignment assignment,
                             MilestoneRecord rawRecord, ResolvedMilestoneStatus resolvedStatus) {
        requireNonNull(studentId);
        requireNonNull(assignment);
        requireNonNull(rawRecord);
        requireNonNull(resolvedStatus);

        this.studentId = studentId;
        this.assignment = assignment;
        this.rawRecord = rawRecord;
        this.resolvedStatus = resolvedStatus;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public AssignmentId getAssignmentId() {
        return assignment.getAssignmentId();
    }

    public DueDate getDueDate() {
        return assignment.getDueDate();
    }

    public MilestoneRecord getRawRecord() {
        return rawRecord;
    }

    public MilestoneStatus getRawStatus() {
        return rawRecord.getStatus();
    }

    public CompletedAt getCompletedAt() {
        return rawRecord.getCompletedAt();
    }

    public ResolvedMilestoneStatus getResolvedStatus() {
        return resolvedStatus;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ResolvedMilestone)) {
            return false;
        }

        ResolvedMilestone otherResolvedMilestone = (ResolvedMilestone) other;
        return studentId.equals(otherResolvedMilestone.studentId)
                && assignment.equals(otherResolvedMilestone.assignment)
                && rawRecord.equals(otherResolvedMilestone.rawRecord)
                && resolvedStatus.equals(otherResolvedMilestone.resolvedStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, assignment, rawRecord, resolvedStatus);
    }

    @Override
    public String toString() {
        return "ResolvedMilestone{"
                + "studentId=" + studentId
                + ", assignment=" + assignment
                + ", rawRecord=" + rawRecord
                + ", resolvedStatus=" + resolvedStatus
                + "}";
    }
}
