package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;

public class AssignmentListTest {

    @Test
    public void constructor_noArgs_startsEmpty() {
        AssignmentList assignmentList = new AssignmentList();
        assertTrue(assignmentList.getAssignmentList().isEmpty());
    }

    @Test
    public void constructor_withArrayList_copiesContents() {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentId id2 = new AssignmentId("A2");

        ArrayList<AssignmentId> source = new ArrayList<>();
        source.add(id1);

        AssignmentList assignmentList = new AssignmentList(source);
        source.add(id2);

        assertEquals(List.of(id1), assignmentList.getAssignmentList());
    }

    @Test
    public void getAssignmentList_returnsUnderlyingContents() throws Exception {
        AssignmentId id1 = new AssignmentId("A1");

        AssignmentList assignmentList = new AssignmentList();
        assignmentList.addAssignment(id1);

        assertEquals(List.of(id1), assignmentList.getAssignmentList());
    }

    @Test
    public void addAssignment_null_throwsNullPointerException() {
        AssignmentList assignmentList = new AssignmentList();
        assertThrows(NullPointerException.class, () -> assignmentList.addAssignment(null));
    }

    @Test
    public void addAssignment_newAssignment_success() {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentList assignmentList = new AssignmentList();

        assertDoesNotThrow(() -> assignmentList.addAssignment(id1));
        assertEquals(List.of(id1), assignmentList.getAssignmentList());
    }

    @Test
    public void addAssignment_duplicateAssignment_throwsAlreadyInGroupException() throws Exception {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentList assignmentList = new AssignmentList();
        assignmentList.addAssignment(id1);

        assertThrows(AlreadyInGroupException.class, () -> assignmentList.addAssignment(id1));
    }

    @Test
    public void removeAssignment_null_throwsNullPointerException() {
        AssignmentList assignmentList = new AssignmentList();
        assertThrows(NullPointerException.class, () -> assignmentList.removeAssignment(null));
    }

    @Test
    public void removeAssignment_missingAssignment_throwsNotInGroupException() {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentList assignmentList = new AssignmentList();

        assertThrows(NotInGroupException.class, () -> assignmentList.removeAssignment(id1));
    }

    @Test
    public void removeAssignment_existingAssignment_success() throws Exception {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentList assignmentList = new AssignmentList();
        assignmentList.addAssignment(id1);

        assertDoesNotThrow(() -> assignmentList.removeAssignment(id1));
        assertTrue(assignmentList.getAssignmentList().isEmpty());
    }

    @Test
    public void toString_reflectsContents() throws Exception {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentList assignmentList = new AssignmentList();
        assignmentList.addAssignment(id1);

        assertEquals(assignmentList.getAssignmentList().toString(), assignmentList.toString());
    }

    @Test
    public void equals_success() throws Exception {
        AssignmentId id1 = new AssignmentId("A1");
        AssignmentId id2 = new AssignmentId("A2");

        AssignmentList first = new AssignmentList();
        AssignmentList second = new AssignmentList();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, "not an assignment list");

        assertEquals(first, second);

        first.addAssignment(id1);
        assertNotEquals(first, second);

        second.addAssignment(id1);
        assertEquals(first, second);

        second.addAssignment(id2);
        assertNotEquals(first, second);
    }

    @Test
    public void hashCode_sameContents_sameHashCode() throws Exception {
        AssignmentId id1 = new AssignmentId("A1");

        AssignmentList first = new AssignmentList();
        AssignmentList second = new AssignmentList();

        first.addAssignment(id1);
        second.addAssignment(id1);

        assertEquals(first.hashCode(), second.hashCode());
    }
}
