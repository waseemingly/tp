package seedu.address.logic.parser.AddRoles;

import seedu.address.logic.commands.addRoles.AddClientRoleCommand;
import seedu.address.logic.commands.addRoles.AddDeveloperRoleCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.developer.DeveloperRoles;

import static java.util.Objects.requireNonNull;

public class AddDeveloperRoleParser implements Parser<AddDeveloperRoleCommand> {
    @Override
    public AddDeveloperRoleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (!args.isEmpty()) {
            String role = args.substring(1);
            return new AddDeveloperRoleCommand(role);
        } else {
            throw new ParseException("Role cannot be empty!");
        }
    }
}
