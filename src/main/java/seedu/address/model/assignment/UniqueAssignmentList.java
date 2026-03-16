package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of assignments that enforces uniqueness between its elements.
 * An assignment is considered a duplicate if it has the same AssignmentId.
 */
public class UniqueAssignmentList implements Iterable<Assignment> {

    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent assignment as the given argument.
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(a -> isSameAssignmentId(a, toCheck));
    }

    /**
     * Adds an assignment to the list.
     * The assignment must not already exist in the list.
     */
    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssignmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in the list.
     * The assignment id of {@code editedAssignment} must not be the same as another existing assignment in the list.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireNonNull(target);
        requireNonNull(editedAssignment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AssignmentNotFoundException();
        }

        // if changing to an AssignmentId that already exists elsewhere, reject
        boolean isDuplicateId =
                internalList.stream()
                        .anyMatch(a -> a != target && isSameAssignmentId(a, editedAssignment));

        if (isDuplicateId) {
            throw new DuplicateAssignmentException();
        }

        internalList.set(index, editedAssignment);
    }

    /**
     * Removes the equivalent assignment from the list.
     * The assignment must exist in the list.
     */
    public void remove(Assignment toRemove) {
        requireNonNull(toRemove);
        boolean removed = internalList.remove(toRemove);
        if (!removed) {
            throw new AssignmentNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments (by AssignmentId).
     */
    public void setAssignments(List<Assignment> assignments) {
        requireNonNull(assignments);
        if (!assignmentsAreUnique(assignments)) {
            throw new DuplicateAssignmentException();
        }
        internalList.setAll(assignments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    private boolean assignmentsAreUnique(List<Assignment> assignments) {
        for (int i = 0; i < assignments.size() - 1; i++) {
            for (int j = i + 1; j < assignments.size(); j++) {
                if (isSameAssignmentId(assignments.get(i), assignments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSameAssignmentId(Assignment a1, Assignment a2) {
        // Requires Assignment#getAssignmentId() to exist.
        return a1.getAssignmentId().equals(a2.getAssignmentId());
    }

    /**
     * Signals that the operation would result in duplicate Assignments (same AssignmentId).
     */
    public static class DuplicateAssignmentException extends RuntimeException {
        public DuplicateAssignmentException() {
            super("Operation would result in duplicate assignments");
        }
    }

    /**
     * Signals that the assignment could not be found in the list.
     */
    public static class AssignmentNotFoundException extends RuntimeException {
        public AssignmentNotFoundException() {
            super("Assignment not found");
        }
    }
}
