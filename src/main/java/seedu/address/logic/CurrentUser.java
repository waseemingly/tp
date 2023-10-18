package seedu.address.logic;

import seedu.address.logic.commands.EditSelfCommand;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.model.person.Person;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.ui.PersonCard;

/**
 * The CurrentUser class represents the current user of the application.
 * It associates a Person object with the current user and provides a way
 * to set the current user using the AddressBookParser.
 */

public class CurrentUser {
    private final Person user;

    /** 
     * Constructs a CurrentUser object with the specified user. Calls class methods of relevant functions to preset
     * the expected behaviours.
     * 
     * @param user The current user logged in.
     */
    CurrentUser(Person user) {
        this.user = user;
        EditCommandParser.setCurrentUserRole(user.getRole());
        EditSelfCommand.setCurrentUser(user);
        AddressBookParser.setCurrentUser(user);
        PersonCard.setCurrentUser(user);
    }
}
