package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.StudentId;

/**
 * Represents the resolved milestone view for a single student.
 *
 * <p>The milestone list is display-ready and should already be ordered in the
 * canonical assignment order supplied by the resolver.
 */
public final class StudentMilestoneView {

    private final StudentId studentId;
    private final List<ResolvedMilestone> milestones;

    /**
     * Constructs a {@code StudentMilestoneView}.
     *
     * @param studentId The student identifier.
     * @param milestones The resolved milestone list in display order.
     */
    public StudentMilestoneView(StudentId studentId, List<ResolvedMilestone> milestones) {
        requireNonNull(studentId);
        requireNonNull(milestones);

        this.studentId = studentId;
        this.milestones = List.copyOf(milestones);
    }

    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns an unmodifiable view of the resolved milestones.
     *
     * @return The resolved milestones in display order.
     */
    public List<ResolvedMilestone> getMilestones() {
        return Collections.unmodifiableList(milestones);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudentMilestoneView)) {
            return false;
        }

        StudentMilestoneView otherView = (StudentMilestoneView) other;
        return studentId.equals(otherView.studentId)
                && milestones.equals(otherView.milestones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, milestones);
    }

    @Override
    public String toString() {
        return "StudentMilestoneView{"
                + "studentId=" + studentId
                + ", milestones=" + milestones
                + "}";
    }
}
