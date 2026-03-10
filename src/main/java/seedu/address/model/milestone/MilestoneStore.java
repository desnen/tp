package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Top-level in-memory milestone store.
 *
 * Structure:
 * studentId -> (assignmentId -> milestoneRecord)
 */
public class MilestoneStore {
    private final Map<StudentId, StudentMilestones> studentMilestones = new HashMap<>();

    public MilestoneStore() {}

    public MilestoneStore(MilestoneStore toCopy) {
        requireNonNull(toCopy);
        setMilestones(toCopy);
    }

    public void setMilestones(MilestoneStore replacement) {
        requireNonNull(replacement);
        studentMilestones.clear();
        for (Map.Entry<StudentId, StudentMilestones> entry : replacement.studentMilestones.entrySet()) {
            studentMilestones.put(entry.getKey(), new StudentMilestones(entry.getValue()));
        }
    }

    public void setMilestone(StudentId studentId, AssignmentId assignmentId, MilestoneRecord milestoneRecord) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);
        requireNonNull(milestoneRecord);

        studentMilestones
                .computeIfAbsent(studentId, unused -> new StudentMilestones())
                .set(assignmentId, milestoneRecord);
    }

    public Optional<MilestoneRecord> getMilestone(StudentId studentId, AssignmentId assignmentId) {
        requireNonNull(studentId);
        requireNonNull(assignmentId);

        if (!studentMilestones.containsKey(studentId)) {
            return Optional.empty();
        }

        return studentMilestones.get(studentId).get(assignmentId);
    }

    public StudentMilestones getStudentMilestones(StudentId studentId) {
        requireNonNull(studentId);
        StudentMilestones milestones = studentMilestones.get(studentId);
        return milestones == null ? new StudentMilestones() : new StudentMilestones(milestones);
    }

    public void removeAllForStudent(StudentId studentId) {
        requireNonNull(studentId);
        studentMilestones.remove(studentId);
    }

    public void removeAllForAssignment(AssignmentId assignmentId) {
        requireNonNull(assignmentId);

        List<StudentId> emptyStudents = new ArrayList<>();
        for (Map.Entry<StudentId, StudentMilestones> entry : studentMilestones.entrySet()) {
            entry.getValue().remove(assignmentId);
            if (entry.getValue().isEmpty()) {
                emptyStudents.add(entry.getKey());
            }
        }

        for (StudentId studentId : emptyStudents) {
            studentMilestones.remove(studentId);
        }
    }

    public boolean isEmpty() {
        return studentMilestones.isEmpty();
    }

    public List<FlatMilestoneEntry> toFlatList() {
        List<FlatMilestoneEntry> flatEntries = new ArrayList<>();
        for (Map.Entry<StudentId, StudentMilestones> studentEntry : studentMilestones.entrySet()) {
            StudentId studentId = studentEntry.getKey();
            for (Map.Entry<AssignmentId, MilestoneRecord> assignmentEntry
                    : studentEntry.getValue().asUnmodifiableMap().entrySet()) {
                flatEntries.add(new FlatMilestoneEntry(
                        studentId,
                        assignmentEntry.getKey(),
                        assignmentEntry.getValue()));
            }
        }
        return flatEntries;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof MilestoneStore)) {
            return false;
        }
        MilestoneStore otherStore = (MilestoneStore) other;
        return studentMilestones.equals(otherStore.studentMilestones);
    }

    @Override
    public int hashCode() {
        return studentMilestones.hashCode();
    }

    @Override
    public String toString() {
        return studentMilestones.toString();
    }
}
