package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DUEDATE_B_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_B_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_A_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_B_TEST;
import static seedu.address.testutil.TypicalAssignments.A_TEST;
import static seedu.address.testutil.TypicalAssignments.B_TEST;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

public class AssignmentTest {

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(A_TEST.isSameAssignment(A_TEST));

        // null -> returns false
        assertFalse(A_TEST.isSameAssignment(null));

        // same label, all other attributes different -> returns true
        Assignment editedAssignment = new AssignmentBuilder(A_TEST)
                .withAssignmentId("A99")
                .build();
        assertTrue(A_TEST.isSameAssignment(editedAssignment));

        // different label, all other attributes same -> returns false
        editedAssignment = new AssignmentBuilder(A_TEST).withLabel(VALID_LABEL_B_TEST).build();
        assertFalse(A_TEST.isSameAssignment(editedAssignment));

        // label differs in case, all other attributes same -> returns false
        editedAssignment = new AssignmentBuilder(A_TEST).withLabel(VALID_LABEL_A_TEST.toLowerCase()).build();
        assertFalse(A_TEST.isSameAssignment(editedAssignment));

        // label has trailing spaces, all other attributes same -> returns false
        String labelWithTrailingSpaces = VALID_LABEL_A_TEST + " ";
        editedAssignment = new AssignmentBuilder(A_TEST).withLabel(labelWithTrailingSpaces).build();
        assertFalse(A_TEST.isSameAssignment(editedAssignment));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment assignmentCopy = new AssignmentBuilder(A_TEST).build();
        assertTrue(A_TEST.equals(assignmentCopy));

        // same object -> returns true
        assertTrue(A_TEST.equals(A_TEST));

        // null -> returns false
        assertFalse(A_TEST.equals(null));

        // different type -> returns false
        assertFalse(A_TEST.equals(5));

        // different assignment -> returns false
        assertFalse(A_TEST.equals(B_TEST));

        // different assignmentId -> returns false
        Assignment editedAssignment = new AssignmentBuilder(A_TEST).withAssignmentId("A88").build();
        assertFalse(A_TEST.equals(editedAssignment));

        // different label -> returns false
        editedAssignment = new AssignmentBuilder(A_TEST).withLabel(VALID_LABEL_B_TEST).build();
        assertFalse(A_TEST.equals(editedAssignment));

        // different group -> returns false
        editedAssignment = new AssignmentBuilder(A_TEST).withGroups(VALID_GROUP_B_TEST).build();
        assertFalse(A_TEST.equals(editedAssignment));

        // different dueDate -> returns false
        editedAssignment = new AssignmentBuilder(A_TEST).withDueDate(VALID_DUEDATE_B_TEST).build();
        assertFalse(A_TEST.equals(editedAssignment));
    }

    @Test
    public void hashCode_sameState_sameHashCode() {
        Assignment assignmentCopy = new AssignmentBuilder(A_TEST).build();
        assertEquals(A_TEST.hashCode(), assignmentCopy.hashCode());

        Assignment differentId = new AssignmentBuilder(A_TEST).withAssignmentId("A88").build();
        assertFalse(A_TEST.hashCode() == differentId.hashCode());

        Assignment differentLabel = new AssignmentBuilder(A_TEST).withLabel(VALID_LABEL_B_TEST).build();
        assertFalse(A_TEST.hashCode() == differentLabel.hashCode());

        Assignment differentGroup = new AssignmentBuilder(A_TEST).withGroups(VALID_GROUP_B_TEST).build();
        assertFalse(A_TEST.hashCode() == differentGroup.hashCode());

        Assignment differentDueDate = new AssignmentBuilder(A_TEST).withDueDate(VALID_DUEDATE_B_TEST).build();
        assertFalse(A_TEST.hashCode() == differentDueDate.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Assignment.class.getCanonicalName()
                + "{assignmentId=" + A_TEST.getAssignmentId()
                + ", label=" + A_TEST.getLabel()
                + ", group=" + A_TEST.getGroups()
                + ", dueDate=" + A_TEST.getDueDate()
                + "}";
        assertEquals(expected, A_TEST.toString());
    }
}
