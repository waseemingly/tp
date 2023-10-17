package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Username {

    public static final String MESSAGE_CONSTRAINTS =
            "Usernames must consist of only alphanumeric characters (letters and digits) and underscores.\n" +
                    "The username must be between 3 and 20 characters in length.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]{3,20}$";

    public final String username;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid username.
     */
    public Username(String name) {
        requireNonNull(name);
        checkArgument(isValidUsername(name), MESSAGE_CONSTRAINTS);
        username = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Username)) {
            return false;
        }

        Username otherName = (Username) other;
        return username.equals(otherName.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
