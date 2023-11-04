package seedu.address.logic.parser.deleteRoles;

import seedu.address.logic.commands.deleteRoles.DeleteDeveloperRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;

public class DeleteDeveloperRoleCommandParser implements Parser<DeleteDeveloperRoleCommand> {
    @Override
    public DeleteDeveloperRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isEmpty()) {
            String role = args.substring(1);
            return new DeleteDeveloperRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
