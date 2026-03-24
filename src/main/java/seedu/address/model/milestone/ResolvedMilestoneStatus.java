package seedu.address.model.milestone;

/**
 * Represents the derived display status of a milestone.
 *
 * <p>This is separate from {@link MilestoneStatus} because some display states,
 * such as OVERDUE, are computed dynamically instead of being stored directly.
 */
public enum ResolvedMilestoneStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    EXEMPT,
    OVERDUE
}
