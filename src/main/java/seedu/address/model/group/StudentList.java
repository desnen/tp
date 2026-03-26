package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.group.exceptions.AlreadyInGroupException;
import seedu.address.model.group.exceptions.NotInGroupException;
import seedu.address.model.person.StudentId;

/**
 * Value object representing the list of Students in a particular Group.
 * Stores a list of StudentIds that correspond to the Students in a particular Group.
 */
public class StudentList {
    public final ArrayList<StudentId> list;

    /**
     * Constructs an {@code StudentList}.
     */
    public StudentList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a filled {@code StudentList}
     * @param students studentId to fill
     */
    public StudentList(ArrayList<StudentId> students) {
        this.list = new ArrayList<>();
        for (StudentId studentId: students) {
            this.list.add(studentId);
        }
    }

    /**
     * Returns the ArrayList of Students that correspond to the Students in the Group.
     * @return The ArrayList of Students as an ArrayList.
     */
    public ArrayList<StudentId> getStudentList() {
        return this.list;
    }

    /**
     * Adds a student identifier to this list.
     *
     * @param id The student identifier to add.
     * @throws NullPointerException if {@code id} is null.
     * @throws AlreadyInGroupException if {@code id} already exists in this list.
     */
    public void addStudent(StudentId id) throws AlreadyInGroupException {
        requireNonNull(id);
        if (list.contains(id)) {
            throw new AlreadyInGroupException(
                    "Specified student is already in this group!");
        }
        list.add(id);
    }

    /**
     * Removes a student identifier from this list.
     *
     * @param id The student identifier to remove.
     * @throws NullPointerException if {@code id} is null.
     * @throws NotInGroupException if {@code id} does not exist in this list.
     */
    public void removeStudent(StudentId id) throws NotInGroupException {
        requireNonNull(id);
        if (!list.contains(id)) {
            throw new NotInGroupException(
                    "Specified student is not in this group!");
        }
        list.remove(id);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StudentList)) {
            return false;
        }
        StudentList otherList = (StudentList) other;
        return list.equals(otherList.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

}
