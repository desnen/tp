---
layout: default.md
title: "User Guide"
pageNav: 3
---

# LeTutor User Guide

LeTutor is a **desktop app for managing students, assignments, and milestone progress**, optimized for use through a **Command Line Interface (CLI)** while still providing the benefits of a Graphical User Interface (GUI). The commands are designed to be concise and consistent so that fast typists can work efficiently.

* * *

## Quick start

1. Ensure **Java 17 or above** is installed on your computer.
2. For macOS, use the exact JDK version specified in the NUS SE-EDU Java installation guide, as mismatched versions may cause the app to fail to start.
3. Download the latest `letutor.jar` from the project’s [GitHub Releases page](https://github.com/AY2526S2-CS2103T-T08-4/tp/releases).
4. Place the file in the folder you want to use as the home folder for LeTutor.
5. Open the command line:
    * **macOS:** Open Launchpad, search for **Terminal**, and open it.
    * **Windows:** Press the Windows key, search for **PowerShell** or **Command Prompt**, and open it.
6. In the command line, run:
   `cd <folder_where_you_saved_letutor.jar>`
   followed by
   `java -jar letutor.jar`
7. After a few seconds, the main window will appear.
8. Type a command in the command box and press <kbd>Enter</kbd> to execute it.  
   For example, typing `help` and pressing <kbd>Enter</kbd> will open the help window.
   Some example commands you can try:

* `list`  
  Lists all students.

* `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`  
  Adds a student named `John Doe`.

* `get /assignments`  
  Lists all assignments.

* `delete /students S1`  
  Deletes the student with ID `S1`.

* `clear`  
  Deletes all students and assignments.

* `exit`  
  Exits the app.

Refer to the [Features](#features) below for details of each command.

* * *

## Features

### Notes about the command format

* LeTutor is controlled almost entirely through commands typed into the command box and confirmed with the Enter key.
* Commands follow a consistent, user-friendly format so that once learned, they are easy to remember and reuse.
* Words in `UPPER_CASE` indicate parameters you must supply.
* Student IDs are shown in the format `S1`, `S2`, `S3`, ...
* Assignment IDs are shown in the format `A1`, `A2`, `A3`, ...
* Fields inside `{ ... }` must be provided in the specified order.
* Multiple groups in a single field should be separated by commas, e.g. `Sec3A, Sec3B`.
* Commands that do not take parameters, such as `help`, `list`, `clear`, and `exit`, ignore extra text after them.

---

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

---

### Adding a student: `add /students`

Adds a student to the address book.

Format: `add /students {<name>; <phone>; <email>; <groups>}`

* The groups field supports multiple groups separated by commas.
* The phone number must contain 8 digits.
* The email should contain an `@`.
* The name, phone, and email fields must not contain the `;` character.

Examples:

* `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`
* `add /students {John Doe; 98765432; johnd@example.com; Sec3A, Math, Chemistry}`

---

### Adding an assignment: `add /assignments`

Adds an assignment to the address book.

Format: `add /assignments {<label>; <groups>; <dueDate>}`

* The groups field supports **multiple groups** separated by commas.
* The `dueDate` must be in the format `YYYY-MM-DD`.  
  Example: `2026-03-20` for 20 March 2026.

Examples:

* `add /assignments {Math; Sec3A; 2026-03-20}`
* `add /assignments {Math; Sec3A, Sec3B; 2026-03-20}`

---

### Listing all students: `list`

Shows a list of all students in the address book.

Format: `list`

---

### Listing all assignments: `get /assignments`

Shows a list of all assignments in the address book.

Format: `get /assignments`

---

### Viewing details of a student: `get /students`

Shows the details of a student in the address book.

Format: `get /students <studentId>`

* Shows the details of the student with the specified `studentId`.
* The `studentId` is the unique identifier of a student and is automatically generated when a student is added.

Example:

* `get /students S1`

---

### Viewing milestone progress for a student: `get /students ... /milestones`

Shows the milestone progress of a student.

Format: `get /students <studentId> /milestones`

* Shows the milestone progress of the student with the specified `studentId`.
* Each milestone corresponds to one assignment.
* A student sees milestones for assignments that share **at least one group** with that student.
* Milestone status is stored internally as either `NOT_STARTED` or `COMPLETED`.
* `OVERDUE` is a view-only status. It is computed automatically when an assignment’s due date has passed and the milestone is still not completed.
* The milestone list is shown in assignment order.

Example:

* `get /students S1 /milestones`

Expected output format:

* `A1 | NOT_STARTED | due=2026-04-01 | completedAt=-`
* `A2 | COMPLETED | due=2026-04-03 | completedAt=2026-03-30T1200H`
* `A3 | OVERDUE | due=2026-03-20 | completedAt=-`

---

### Updating one milestone for a student: `set /students ... /milestones`

Sets the milestone status of one assignment for one student.

Format: `set /students <studentId> /milestones <assignmentId> <status> [completedAt]`

* The `studentId` is the unique identifier of a student and is shown as values such as `S1`, `S2`, `S3`, ...
* The `assignmentId` is the unique identifier of an assignment and is shown as values such as `A1`, `A2`, `A3`, ...
* Only the following stored milestone statuses are allowed:
    * `NOT_STARTED`
    * `COMPLETED`
* If the status is `NOT_STARTED`, do not provide a `completedAt` value.
* If the status is `COMPLETED`, a `completedAt` value must be provided.
* `OVERDUE` cannot be set manually because it is computed automatically for display.
* A milestone can only be set for an assignment that shares **at least one group** with the student.
* If the student does not exist, the assignment does not exist, or the student and assignment do not share a group, the command will fail.

Examples:

* `set /students S1 /milestones A1 NOT_STARTED`
* `set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H`

Notes:

* Use `NOT_STARTED` to reset a milestone to incomplete.
* Use `COMPLETED <completedAt>` to mark a milestone as completed.
* The `completedAt` value should follow the app’s accepted timestamp format, e.g. `2026-03-30T1200H`.

---

### Viewing a specific assignment: `get /assignments <assignmentId>`

Shows the selected assignment in the app.

Format: `get /assignments <assignmentId>`

* Shows the assignment with the specified `assignmentId`.
* The `assignmentId` is the unique identifier of an assignment and is automatically generated when an assignment is added.
* Assignment IDs are shown in the format `A1`, `A2`, `A3`, ...

Example:

* `get /assignments A1`

---

### Locating students by name: `find /students`

Finds students whose names contain any of the given keywords.

Format: `find /students <keywords>`

* The search is case-insensitive.  
  e.g. `hans` will match `Hans`
* The order of the keywords does not matter.  
  e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched.  
  e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).  
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

* `find /students John` returns students such as `John Doe`
* `find /students alex david` returns students such as `Alex Yeoh`, `David Li`

![result for 'find alex david'](images/findAlexDavidResult.png)

---

### Locating students and assignments by group: `find /groups`

Finds all students and assignments that belong to the specified group.

Format: `find /groups <groupName>`

* Filters the student list to show only students in the specified group.
* Filters the assignment list to show only assignments tagged to the specified group.
* The group name should match an existing group name exactly.
* If no matching group is found, no students and no assignments will be shown.

Example:

* `find /groups Science`

Expected result message format:

* `X persons listed and Y assignments listed for Group "Science"`

### Editing a student: `edit /students`

Edits the details of a student in the address book.

Format: `edit /students <studentId> {<name>; <phone>; <email>; <groups>}`

* Edits the student with the specified `studentId`.
* You may leave fields empty if you do not wish to edit them.
* To edit only the name, you can use:  
  `edit /students S1 {John; ; ;}`
* The groups field follows the same format as adding a student, using commas to separate multiple groups.

Examples:

* `edit /students S1 {John Doe; 98765432; johnd@mail.com; Sec3B}`
* `edit /students S2 {John; ; ;}`

---

### Editing an assignment: `edit /assignments`

Edits the details of an assignment in the address book.

Format: `edit /assignments <assignmentId> {<label>; <groups>; <dueDate>}`

* Edits the assignment with the specified `assignmentId`.
* You may leave fields empty if you do not wish to edit them.
* To edit only the label, you can use:  
  `edit /assignments A1 {Quiz 2; ; }`
* The groups field supports multiple groups separated by commas.

Examples:

* `edit /assignments A1 {A-Testing; Sec3A; 2026-04-30}`
* `edit /assignments A2 {Math Worksheet; Sec3A, Sec3B; 2026-05-01}`

---

### Deleting a student: `delete /students`

Deletes the specified student from the address book.

Format: `delete /students <studentId>`

* Deletes the student with the specified `studentId`.

Example:

* `delete /students S3`

---

### Deleting an assignment: `delete /assignments`

Deletes the specified assignment from the address book.

Format: `delete /assignments <assignmentId>`

* Deletes the assignment with the specified `assignmentId`.

Example:

* `delete /assignments A2`

---

### Clearing all entries: `clear`

Clears all student and assignment entries from the address book.

Format: `clear`

---

### Exiting the program: `exit`

Exits the program.

Format: `exit`

---

### Saving the data

LeTutor data is saved automatically to the hard disk after any command that changes the data. There is no need to save manually.

---

### Editing the data file

LeTutor data is saved automatically as a JSON file at:

`[JAR file location]/data/addressbook.json`

Advanced users may update data directly by editing the data file.

**Caution:** If your changes make the data file invalid, LeTutor will discard all data and start with an empty data file on the next run. It is recommended to keep a backup before editing the file.

Certain edits can also cause LeTutor to behave in unexpected ways, such as when values fall outside the accepted range. Edit the data file only if you are confident that you can update it correctly.

* * *

## FAQ

**Q:** How do I transfer my data to another computer?  
**A:** Install the app on the other computer and overwrite the empty data file it creates with the data file from your previous LeTutor home folder.

* * *

## Command summary

**Add student**  
`add /students {<name>; <phone>; <email>; <groups>}`  
Example: `add /students {John Doe; 98765432; johnd@example.com; Sec3A}`

**Add assignment**  
`add /assignments {<label>; <groups>; <dueDate>}`  
Example: `add /assignments {Math; Sec3A, Sec3B; 2026-03-20}`

**List all students**  
`list`

**List all assignments**  
`get /assignments`

**Get student**  
`get /students <studentId>`  
Example: `get /students S3`

**Get student milestones**  
`get /students <studentId> /milestones`  
Example: `get /students S1 /milestones`

**Get assignment**  
`get /assignments <assignmentId>`  
Example: `get /assignments A2`

**Find student**  
`find /students <keywords>`  
Example: `find /students alex david`

**Find group**  
`find /groups <groupName>`  
Example: `find /groups Science`

**Edit student**  
`edit /students <studentId> {<name>; <phone>; <email>; <groups>}`  
Example: `edit /students S1 {John Doe; 98765432; johnd@mail.com; Sec3B}`

**Edit assignment**  
`edit /assignments <assignmentId> {<label>; <groups>; <dueDate>}`  
Example: `edit /assignments A1 {Quiz 2; Sec3A, Sec3B; 2026-04-01}`

**Set milestone**  
`set /students <studentId> /milestones <assignmentId> <status> [completedAt]`  
Examples:
* `set /students S1 /milestones A1 NOT_STARTED`
* `set /students S1 /milestones A1 COMPLETED 2026-03-30T1200H`

**Delete student**  
`delete /students <studentId>`  
Example: `delete /students S3`

**Delete assignment**  
`delete /assignments <assignmentId>`  
Example: `delete /assignments A2`

**Clear**  
`clear`

**Help**  
`help`

**Exit**  
`exit`