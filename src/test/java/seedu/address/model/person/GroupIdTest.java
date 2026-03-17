package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupId(null));
    }

    @Test
    public void constructor_invalidGroupId_throwsIllegalArgumentException() {
        String invalidGroupId = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupId(invalidGroupId));
    }

    @Test
    public void isValidGroupId() {
        // null groupId
        assertFalse(GroupId.isValidGroupId(null));

        // invalid groupId
        assertFalse(GroupId.isValidGroupId("")); // empty string
        assertFalse(GroupId.isValidGroupId(" ")); // spaces only
        assertFalse(GroupId.isValidGroupId("   G1")); // leading spaces

        // valid groupId
        assertTrue(GroupId.isValidGroupId("G1"));
        assertTrue(GroupId.isValidGroupId("Tutorial 3A"));
    }

    @Test
    public void equals() {
        GroupId groupId = new GroupId("G1");

        // same values -> returns true
        assertTrue(groupId.equals(new GroupId("G1")));

        // same object -> returns true
        assertTrue(groupId.equals(groupId));

        // null -> returns false
        assertFalse(groupId.equals(null));

        // different types -> returns false
        assertFalse(groupId.equals(5.0f));

        // different values -> returns false
        assertFalse(groupId.equals(new GroupId("G2")));
    }
}
