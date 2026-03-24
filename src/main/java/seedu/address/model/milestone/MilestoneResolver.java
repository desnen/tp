package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.person.StudentId;

/**
 * Resolves stored milestone data into a display-ready per-student milestone view.
 *
 * <p>This resolver is responsible for:
 * <ul>
 *   <li>filling in missing milestones with a default NOT_STARTED record</li>
 *   <li>computing derived OVERDUE status from due date and raw status</li>
 *   <li>preserving the input assignment order for UI display</li>
 * </ul>
 */
public final class MilestoneResolver {

    /**
     * Default virtual milestone record used when no raw milestone exists for a
     * student-assignment pair.
     */
    public static final MilestoneRecord DEFAULT_MILESTONE_RECORD =
            new MilestoneRecord(MilestoneStatus.NOT_STARTED, new CompletedAt(""));

    /**
     * Resolves the milestones for one student using today's date.
     *
     * @param studentId The student identifier.
     * @param assignments The full assignment list in canonical display order.
     * @param studentMilestones The student's raw sparse milestone data.
     * @return A display-ready resolved milestone view.
     */
    public StudentMilestoneView resolveForStudent(StudentId studentId,
                                                  List<Assignment> assignments,
                                                  StudentMilestones studentMilestones) {
        requireNonNull(studentId);
        requireNonNull(assignments);
        requireNonNull(studentMilestones);

        return resolveForStudent(studentId, assignments, studentMilestones, LocalDate.now());
    }

    /**
     * Resolves the milestones for one student using a supplied date.
     *
     * <p>This overload is mainly useful for deterministic tests.
     *
     * @param studentId The student identifier.
     * @param assignments The full assignment list in canonical display order.
     * @param studentMilestones The student's raw sparse milestone data.
     * @param today The date to use when computing overdue.
     * @return A display-ready resolved milestone view.
     */
    public StudentMilestoneView resolveForStudent(StudentId studentId,
                                                  List<Assignment> assignments,
                                                  StudentMilestones studentMilestones,
                                                  LocalDate today) {
        requireNonNull(studentId);
        requireNonNull(assignments);
        requireNonNull(studentMilestones);
        requireNonNull(today);

        List<ResolvedMilestone> resolvedMilestones = new ArrayList<>();

        for (Assignment assignment : assignments) {
            AssignmentId assignmentId = assignment.getAssignmentId();
            Optional<MilestoneRecord> maybeRawRecord = studentMilestones.get(assignmentId);

            MilestoneRecord rawRecord;
            if (maybeRawRecord.isPresent()) {
                rawRecord = maybeRawRecord.get();
            } else {
                rawRecord = DEFAULT_MILESTONE_RECORD;
            }

            ResolvedMilestoneStatus resolvedStatus = resolveStatus(rawRecord, assignment, today);

            resolvedMilestones.add(new ResolvedMilestone(
                    studentId,
                    assignment,
                    rawRecord,
                    resolvedStatus
            ));
        }

        return new StudentMilestoneView(studentId, resolvedMilestones);
    }

    /**
     * Resolves the display status for a milestone.
     *
     * <p>Rules:
     * <ul>
     *   <li>COMPLETED stays COMPLETED</li>
     *   <li>EXEMPT stays EXEMPT</li>
     *   <li>IN_PROGRESS and NOT_STARTED become OVERDUE if due date has passed</li>
     *   <li>otherwise the raw status is preserved</li>
     * </ul>
     *
     * @param rawRecord The stored or default milestone record.
     * @param assignment The assignment metadata.
     * @param today The date used to compute overdue.
     * @return The derived display status.
     */
    private ResolvedMilestoneStatus resolveStatus(MilestoneRecord rawRecord,
                                                  Assignment assignment,
                                                  LocalDate today) {
        requireNonNull(rawRecord);
        requireNonNull(assignment);
        requireNonNull(today);

        MilestoneStatus rawStatus = rawRecord.getStatus();

        if (rawStatus == MilestoneStatus.COMPLETED) {
            return ResolvedMilestoneStatus.COMPLETED;
        }

        if (rawStatus == MilestoneStatus.EXEMPT) {
            return ResolvedMilestoneStatus.EXEMPT;
        }

        if (assignment.getDueDate().date.isBefore(today)) {
            return ResolvedMilestoneStatus.OVERDUE;
        }

        if (rawStatus == MilestoneStatus.IN_PROGRESS) {
            return ResolvedMilestoneStatus.IN_PROGRESS;
        }

        return ResolvedMilestoneStatus.NOT_STARTED;
    }
}
