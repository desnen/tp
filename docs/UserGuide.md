---
  layout: default.md
    - title: "User Guide"
  pageNav: 3
---

# LeTutor User Guide

LeTutor is a **desktop app for managing students and assignments, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). The commands are designed to be concise and consistent so that fast typists can work more efficiently than with traditional mouse-driven apps.


<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure Java 17 or above is installed on your computer.
2. For macOS, use the exact JDK version specified in the NUS SE-EDU Java installation guide, as mismatched versions may cause the app to fail to start.
3. Download the latest letutor.jar release file from the project’s GitHub releases page here[link here].
4. Place the file to the folder you want to use as the home folder for LeTutor.
5. Open the command line:
   * On macOS, Open Launchpad, search for Terminal and open it.
   * On Windows, Press the Windows key and search for PowerShell or Command Prompt, then open it
6.In the command line, run: cd <folder_where_you_saved_letutor.jar>, followed by java -jar letutor.jar
7. After a few seconds, the main window will appear.




1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all students

   * `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`: Adds a student named `John Doe` to the Address Book.

   * `delete /students 3` : Deletes the 3rd students shown in the current list.

   * `clear` : Deletes all students and assignments.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* LeTutor is controlled almost entirely through commands typed into the command box and confirmed with the Enter key.

* Commands follow a consistent, user-friendly format so that once learned, they are easy to remember and reuse.

* Words in UPPER_CASE indicate parameters you must supply. For example, in add /students {<NAME>; <PHONE NO.>; <EMAIL>; <GROUP>}, you replace <NAME> with the student’s actual name.

* Items followed by ... can be repeated any number of times, each item is separated by a , For example, in the <GROUP> field for students we can have G1, G2, G3 …

* Parameters have to be provided in the specified order during creation of Students and Assignments.

* Commands that do not take parameters (such as help, list, clear, exit) ignore any extra text after them, so help 123 will be treated as help.


</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a student: `add /students`

Adds a student to the address book.

Format: `add /students {<name>; <phone>; <email>; <group>}`

* The groups field allows for multiple groups to be added. Each new group will have to be separated by a `,`.
* Take note that the phone number inputted has to be valid with 8 numbers.
* Ensure that your email has to include an @.
* Make sure that your name, phone and emails don't include the `;` character.

Examples: 
* `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`
* `add /students {John Doe; 98765432; johnd@example.com; Sec3A, Math, Chemistry}`

### Adding an assignment: `add /assignments`

Adds an assignment to the address book.

Format: `add /assignments {<label>; <group>; <dueDate>}`

* The `dueDate` should be in the format `YYYY-MM-DD`. e.g. `2026-03-20` for 20 March 2026.

Example: `add /assignments {Math; Sec3A; 2026-03-20}`

### Listing all students : `list`

Shows a list of all students in the address book.

Format: `list`

### Listing all assignments: `get /assignments`

Shows a list of all assignments in the address book.

Format: `get /assignments`

### Viewing details of a student: `get /students`

Shows the details of a student in the address book.

Format: `get /students <studentId>`

* Shows the details of the student with the specified `studentId`.
* The `studentId` is the unique identifier of a student, which is automatically generated when a student is added to the address book.
* Student IDs are shown in the format `S1`, `S2`, `S3`, ...

Example: `get /students S1`

### Viewing milestone progress for a student: `get /students ... /milestones`

Shows the milestone progress of a student.

Format: `get /students <studentId> /milestones`

* Shows the milestone progress of the student with the specified `studentId`.
* The `studentId` is the unique identifier of a student and is shown in the app as values such as `S1`, `S2`, `S3`, ...
* Each milestone corresponds to one assignment.
* A student only sees milestones for assignments that belong to the same group as the student.
* Milestone status is stored internally as either `NOT_STARTED` or `COMPLETED`.
* `OVERDUE` is a view-only status. It is computed automatically when an assignment’s due date has passed and the milestone is still not completed.
* The milestone list is shown in assignment order.

Example: `get /students S1 /milestones`

Expected output format:
* `A1 | NOT_STARTED | due=2026-04-01 | completedAt=-`
* `A2 | COMPLETED | due=2026-04-03 | completedAt=2026-03-30T1200H`
* `A3 | OVERDUE | due=2026-03-20 | completedAt=-`

### Updating one milestone for a student: `set /students ... /milestones`

Sets the milestone status of one assignment for one student.

Format: `set /students <studentId> /milestones <assignmentId> <status> [completedAt]`

* The `studentId` is the unique identifier of a student and is shown in the app as values such as `S1`, `S2`, `S3`, ...
* The `assignmentId` is the unique identifier of an assignment and is shown in the app as values such as `A1`, `A2`, `A3`, ...
* Only the following stored milestone statuses are allowed:
    * `NOT_STARTED`
    * `COMPLETED`
* If the status is `NOT_STARTED`, do not provide a `completedAt` value.
* If the status is `COMPLETED`, a `completedAt` value must be provided.
* `OVERDUE` cannot be set manually because it is computed automatically for display.
* A milestone can only be set for an assignment that belongs to the same group as the student.
* If the student does not exist, the assignment does not exist, or the assignment is not in the student’s group, the command will fail.

Examples:
* `set /students S1 /milestones A1 NOT_STARTED`
* `set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H`

Notes:
* Use `NOT_STARTED` to reset a milestone to incomplete.
* Use `COMPLETED <completedAt>` to mark a milestone as completed.
* The `completedAt` value should follow the app’s accepted timestamp format.

### View details of an assignment: `get /assignments`

Shows the details of an assignment in the address book.

Format: `get /assignments <assignmentId>`

* Shows the details of the assignment with the specified `assignmentId`.
* The `assignmentId` is the unique identifier of an assignment, which is automatically generated when an assignment is added to the address book.
* Assignment IDs are shown in the format `A1`, `A2`, `A3`, ...

Example: `get /assignments A1`

### Locating students by name: `find /students`


Finds students whose names contain any of the given keywords.

Format: `find /students <keywords>`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find /students John` returns `john` and `John Doe`
* `find /students alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Editing a student: `edit /students`

Edits the details of a student in the address book.

Format: `edit /students <studentId> {<name>; <phone>; <email>; <group>}`

* Edits the person at the specified `studentId`
* Empty fields can be added if you do not wish to edit that particular field. For example: If you only want to edit the name of a student, you would enter `edit /students <studentId> {John; ; ;}`.
* Edits to Groups field follows the same format for adding a student, use the `,` symbol to separate different group names.

Example: `edit /students 1 {John Doe; 98765432; johnd@mail.com; Sec3B}`

### Deleting a student : `delete /students`

Deletes the specified student from the address book.

Format: `delete /students <studentId>`

* Deletes the student at the specified `studentId`.

Example: `delete /students S3`

### Deleting an assignment : `delete /assignments`

Deletes the specified assignment from the address book.

Format: `delete /assignments <assignmentId>`

* Deletes the assignment at the specified `assignmentId`.

Example: `delete /assignments 2`

### Clearing all entries : `clear`

Clears all student and assignment entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, LeTutor will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause LeTutor to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LeTutor home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|-------------------------------------------------------------------------------------------------------------------------------
**Add Student**  | `add /students {<name>; <phone>; <email>; <group>}` <br> e.g., `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`
**Add Assignment**  | `add /assignments {<label>; <group>; <dueDate>}` <br> e.g., `add /assignments {Math; Sec3A; 2026-03-20}`
**List All Students**   | `list`
**List All Assignments**   | `get /assignments`
**Get Student**  | `get /students <studentId>` <br> e.g., `get /students 3`
**Get Assignment**  | `get /assignments <assignmentId>` <br> e.g., `get /assignments 2`
**Find Student**  | `find /students <keywords>` <br> e.g., `find /students alex david`
**Edit Student**  | `edit /students <studentId> {<name>; <phone>; <email>; <group>}` <br> e.g., `edit /students 1 {John Doe; 98765432; johnd@mail.com; Sec3B}`
**Delete Student**  | `delete /students <studentId>` <br> e.g., `delete /students 3`
**Delete Assignment** | `delete /assignments <assignmentId>` <br> e.g `delete /assignments 2`
**Clear**  | `clear`
**Help**   | `help`
