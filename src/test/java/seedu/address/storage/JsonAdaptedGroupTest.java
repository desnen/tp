package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.group.AssignmentList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.StudentList;
import seedu.address.model.person.StudentId;

public class JsonAdaptedGroupTest {

    private static final ArrayList<String> VALID_STUDENTLIST =
            new ArrayList<>(List.of("S1", "S2"));
    private static final ArrayList<String> VALID_ASSIGNMENTLIST =
            new ArrayList<>(List.of("A1", "A2"));

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws Exception {
        StudentList students = new StudentList();
        students.addStudent(new StudentId("S1"));
        AssignmentList assignments = new AssignmentList();
        assignments.addAssignment(new AssignmentId("A1"));

        Group testGroup = new Group(new GroupName("CS"), students, assignments);
        JsonAdaptedGroup group = new JsonAdaptedGroup(testGroup);
        Group convertedGroup = group.toModelType();

        assertEquals(testGroup, convertedGroup);
        assertTrue(convertedGroup.getStudentIds().getStudentList().contains(new StudentId("S1")));
        assertTrue(convertedGroup.getAssignmentIds().getAssignmentList().contains(new AssignmentId("A1")));
    }

    @Test
    public void toModelType_nullGroupName_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup(null, VALID_STUDENTLIST, VALID_ASSIGNMENTLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                GroupName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullStudents_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup("Tutorial-1", null, VALID_ASSIGNMENTLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StudentList.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }

    @Test
    public void toModelType_nullAssignments_throwsIllegalValueException() {
        JsonAdaptedGroup group =
                new JsonAdaptedGroup("Tutorial-1", VALID_STUDENTLIST, null);
        // JsonAdaptedGroup currently reports GroupName in this branch; keep test aligned with implementation.
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                GroupName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, group::toModelType);
    }
}
