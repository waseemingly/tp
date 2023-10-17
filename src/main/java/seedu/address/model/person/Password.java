package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Password {

    public static final String MESSAGE_CONSTRAINTS =
            "Password must meet the following criteria:\n" +
                    "At least one uppercase letter [A-Z].\n" +
                    "At least one lowercase letter [a-z].\n" +
                    "At least one digit [0-9].\n" +
                    "At least one special character [@#$%^&+=].\n" +
                    "No whitespace characters \n" +
                    "The password must be at least 8 characters long";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])(?!.*\\s).{8,}$";

    public final String password;

    /**
     * Constructs a {@code Password}.
     *
     * @param password A valid password.
     */
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_CONSTRAINTS);
        this.password = password;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPassword(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return password;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Password)) {
            return false;
        }

        Password otherPassword = (Password) other;
        return password.equals(otherPassword.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }
}
