package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseTuple3;
import static seedu.address.logic.parser.ParserUtil.parseTuple3AllowEmpty;

import java.util.List;

import seedu.address.logic.commands.EditAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Group;
import seedu.address.model.assignment.Label;

public class EditAssignmentCommandParser implements Parser<EditAssignmentCommand> {

    private static final String PATH_ASSIGNMENT = "/assignment";

    @Override
    public EditAssignmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmed = args.trim();

        if (trimmed.isEmpty() || !trimmed.startsWith(PATH_ASSIGNMENT)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE));
        }

        // Expect: /assignment <assignmentId> {label; group; dueDate}
        String remainder = trimmed.substring(PATH_ASSIGNMENT.length()).trim();
        String[] idAndTuple = remainder.split("\\s+", 2);

        if (idAndTuple.length < 2) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE));
        }

        AssignmentId assignmentId = ParserUtil.parseAssignmentId(idAndTuple[0]);
        List<String> parts = parseTuple3AllowEmpty(idAndTuple[1]);

        EditAssignmentCommand.EditAssignmentDescriptor descriptor =
                new EditAssignmentCommand.EditAssignmentDescriptor();

        String labelRaw = parts.get(0).trim();
        if (!labelRaw.isEmpty()) {
            descriptor.setLabel(ParserUtil.parseLabel(labelRaw));
        }

        String groupRaw = parts.get(1).trim();
        if (!groupRaw.isEmpty()) {
            descriptor.setGroup(ParserUtil.parseGroup(groupRaw));
        }

        String dueDateRaw = parts.get(2).trim();
        if (!dueDateRaw.isEmpty()) {
            descriptor.setDueDate(ParserUtil.parseDueDate(dueDateRaw));
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAssignmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAssignmentCommand(assignmentId, descriptor);
    }
}
