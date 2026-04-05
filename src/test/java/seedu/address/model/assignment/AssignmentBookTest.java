package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.A_TEST;
import static seedu.address.testutil.TypicalAssignments.B_TEST;
import static seedu.address.testutil.TypicalAssignments.getTypicalAssignmentBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.testutil.AssignmentBuilder;

public class AssignmentBookTest {

    private final AssignmentBook assignmentBook = new AssignmentBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), assignmentBook.getAssignmentList());
    }

    @Test
    public void copyConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignmentBook(null));
    }

    @Test
    public void copyConstructor_withValidAssignmentBook_copiesData() {
        AssignmentBook source = getTypicalAssignmentBook().getAssignmentBook();
        AssignmentBook copy = new AssignmentBook(source);

        assertEquals(source.getAssignmentList(), copy.getAssignmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentBook.resetData(null));
    }

    @Test
    public void resetData_withValidAssignmentBook_replacesData() {
        AssignmentBook newData = getTypicalAssignmentBook().getAssignmentBook();
        assignmentBook.resetData(newData);
        assertEquals(newData.getAssignmentList(), assignmentBook.getAssignmentList());
    }

    @Test
    public void hasAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentBook.hasAssignment(null));
    }

    @Test
    public void hasAssignment_assignmentNotInBook_returnsFalse() {
        assertFalse(assignmentBook.hasAssignment(A_TEST));
    }

    @Test
    public void hasAssignment_assignmentInBook_returnsTrue() {
        assignmentBook.addAssignment(A_TEST);
        assertTrue(assignmentBook.hasAssignment(A_TEST));
    }

    @Test
    public void hasAssignment_assignmentWithSameIdInBook_returnsTrue() {
        assignmentBook.addAssignment(A_TEST);
        Assignment editedAssignment = new AssignmentBuilder(A_TEST)
                .withLabel("Another Label")
                .withDueDate("2026-12-12")
                .build();

        assertTrue(assignmentBook.hasAssignment(editedAssignment));
    }

    @Test
    public void addAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentBook.addAssignment(null));
    }

    @Test
    public void addAssignment_duplicateAssignment_throwsDuplicateAssignmentException() {
        assignmentBook.addAssignment(A_TEST);
        assertThrows(DuplicateAssignmentException.class, () -> assignmentBook.addAssignment(A_TEST));
    }

    @Test
    public void setAssignment_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentBook.setAssignment(null, A_TEST));
    }

    @Test
    public void setAssignment_nullEditedAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentBook.setAssignment(A_TEST, null));
    }

    @Test
    public void setAssignment_targetNotInBook_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> assignmentBook.setAssignment(A_TEST, B_TEST));
    }

    @Test
    public void setAssignment_validTargetAndEditedAssignment_replacesAssignment() {
        assignmentBook.addAssignment(A_TEST);
        assignmentBook.setAssignment(A_TEST, B_TEST);

        assertFalse(assignmentBook.hasAssignment(A_TEST));
        assertTrue(assignmentBook.hasAssignment(B_TEST));
    }

    @Test
    public void removeAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentBook.removeAssignment(null));
    }

    @Test
    public void removeAssignment_assignmentNotInBook_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> assignmentBook.removeAssignment(A_TEST));
    }

    @Test
    public void removeAssignment_existingAssignment_removesAssignment() {
        assignmentBook.addAssignment(A_TEST);
        assignmentBook.removeAssignment(A_TEST);

        assertFalse(assignmentBook.hasAssignment(A_TEST));
    }

    @Test
    public void getAssignmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> assignmentBook.getAssignmentList().remove(0));
    }
}
