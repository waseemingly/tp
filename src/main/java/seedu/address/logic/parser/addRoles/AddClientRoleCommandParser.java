package seedu.address.logic.parser.addRoles;

import seedu.address.logic.commands.addRoles.AddClientRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;

public class AddClientRoleCommandParser implements Parser<AddClientRoleCommand> {
    @Override
    public AddClientRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isEmpty()) {
            String role = args.substring(1);
            return new AddClientRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
