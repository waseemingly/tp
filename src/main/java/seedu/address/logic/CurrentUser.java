package seedu.address.logic;

import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.person.Person;

public class CurrentUser {

    private final Person user;

    public CurrentUser(Person user) {
        this.user = user;
        AddressBookParser.setCurrentUser(user);
    }
}