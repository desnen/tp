package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing a student identifier.
 */
public final class StudentId {
    public static final String MESSAGE_CONSTRAINTS = "StudentId cannot be blank.";

    private final String value;

    public StudentId(String value) {
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
        if (!(other instanceof StudentId)) {
            return false;
        }
        StudentId otherStudentId = (StudentId) other;
        return value.equals(otherStudentId.value);
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
