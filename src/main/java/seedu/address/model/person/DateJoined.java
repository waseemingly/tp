package seedu.address.model.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the date a person Joined. In the format: dd-MM-YYYY
 */
public class DateJoined {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the format dd-MM-yyyy. Eg: 31-12-2019";
    public static final String VALIDATION_REGEX = "[0-3]\\d-[01]\\d-\\d{4}";
    public final Date value;

    /**
     * Constructs a {@code DateJoined}.
     *
     * @param date A valid date string.
     */
    public DateJoined(String date) throws ParseException {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = new SimpleDateFormat("dd-MM-yyyy").parse(date);

    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String text) {
        if (text == null || !text.matches(VALIDATION_REGEX))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd-MM-yyyy").format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateJoined)) {
            return false;
        }

        DateJoined otherDate = (DateJoined) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
