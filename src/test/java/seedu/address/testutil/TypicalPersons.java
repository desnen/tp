package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withStudentId("S1").withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withGroup("CS").build();

    public static final Person BENSON = new PersonBuilder().withStudentId("S2").withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGroup("CS").build();

    public static final Person CARL = new PersonBuilder().withStudentId("S3").withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com").withGroup("CS").build();

    public static final Person DANIEL = new PersonBuilder().withStudentId("S4").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withGroup("CS")
            .build();

    public static final Person ELLE = new PersonBuilder().withStudentId("S5").withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com").withGroup("CS").build();

    public static final Person FIONA = new PersonBuilder().withStudentId("S6").withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com").withGroup("CS").build();

    public static final Person GEORGE = new PersonBuilder().withStudentId("S7").withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com").withGroup("CS").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withStudentId("S8").withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com").withGroup("CS").build();

    public static final Person IDA = new PersonBuilder().withStudentId("S9").withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com").withGroup("CS").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withStudentId("S10").withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withGroup(VALID_GROUP_AMY).build();

    public static final Person BOB = new PersonBuilder().withStudentId("S11").withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withGroup(VALID_GROUP_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier";

    private TypicalPersons() {}

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
