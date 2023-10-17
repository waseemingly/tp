package seedu.address.logic;

import seedu.address.logic.commands.EditSelfCommand;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.model.person.Person;

public class DummyCurrentUser {
    private final Person user;

    /** 
     * Constructor for CurrentUser that will call the setRole method of relevant functions to preset the expected
     * behaviours.
     * 
     * @param user The current user logged in.
     */
    DummyCurrentUser(Person user) {
        this.user = user;
        EditCommandParser.setRole(user.getRole());
        EditSelfCommand.setCurrentUser(user);
    }
}
