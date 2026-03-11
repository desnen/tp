package seedu.address.model.milestone;

import static java.util.Objects.requireNonNull;

/**
 * Value object representing a completion timestamp.
 * Valid values are either empty string, or YYYY-MM-DDTHHMMH.
 */
public final class CompletedAt {
    public static final String VALIDATION_REGEX = "^$|^\\d{4}-\\d{2}-\\d{2}T\\d{4}H$";
    public static final String MESSAGE_CONSTRAINTS =
            "CompletedAt must be empty or in the format YYYY-MM-DDTHHMMH.";

    private final String value;

    public CompletedAt(String value) {
        requireNonNull(value);
        String trimmedValue = value.trim();
        if (!isValidCompletedAt(trimmedValue)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = trimmedValue;
    }

    public static boolean isValidCompletedAt(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CompletedAt)) {
            return false;
        }
        CompletedAt otherCompletedAt = (CompletedAt) other;
        return value.equals(otherCompletedAt.value);
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
