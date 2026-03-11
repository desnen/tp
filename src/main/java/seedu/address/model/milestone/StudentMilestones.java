package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Stores all milestone records for a single student.
 */
public class StudentMilestones {
    private final Map<AssignmentId, MilestoneRecord> milestonesByAssignment = new HashMap<>();

    public StudentMilestones() {}

    public StudentMilestones(StudentMilestones toCopy) {
        requireNonNull(toCopy);
        milestonesByAssignment.putAll(toCopy.milestonesByAssignment);
    }

    public void set(AssignmentId assignmentId, MilestoneRecord milestoneRecord) {
        requireNonNull(assignmentId);
        requireNonNull(milestoneRecord);
        milestonesByAssignment.put(assignmentId, milestoneRecord);
    }

    public Optional<MilestoneRecord> get(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        return Optional.ofNullable(milestonesByAssignment.get(assignmentId));
    }

    public void remove(AssignmentId assignmentId) {
        requireNonNull(assignmentId);
        milestonesByAssignment.remove(assignmentId);
    }

    public boolean isEmpty() {
        return milestonesByAssignment.isEmpty();
    }

    public Map<AssignmentId, MilestoneRecord> asUnmodifiableMap() {
        return Collections.unmodifiableMap(milestonesByAssignment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StudentMilestones)) {
            return false;
        }
        StudentMilestones otherStudentMilestones = (StudentMilestones) other;
        return milestonesByAssignment.equals(otherStudentMilestones.milestonesByAssignment);
    }

    @Override
    public int hashCode() {
        return milestonesByAssignment.hashCode();
    }

    @Override
    public String toString() {
        return milestonesByAssignment.toString();
    }
}
