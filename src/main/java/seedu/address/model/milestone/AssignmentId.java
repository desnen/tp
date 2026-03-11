package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing an assignment identifier.
 */
public final class AssignmentId {
    public static final String MESSAGE_CONSTRAINTS = "AssignmentId cannot be blank.";

    private final String value;

    public AssignmentId(String value) {
        requireNonNull(value);
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = trimmedValue;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AssignmentId)) {
            return false;
        }
        AssignmentId otherAssignmentId = (AssignmentId) other;
        return value.equals(otherAssignmentId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
