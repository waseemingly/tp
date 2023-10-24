package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Client.Client;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_FILE = "File does not exist!\n";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW =
                "These are the %1$d employees with matching information";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Developer developer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(developer.getName())
                .append("; Phone: ")
                .append(developer.getPhone())
                .append("; Email: ")
                .append(developer.getEmail())
                .append("; Address: ")
                .append(developer.getAddress())
                .append("; Date Joined: ")
                .append(developer.getDateJoined())
                .append("; Role: ")
                .append(developer.getRole())
                .append("; Salary: ")
                .append(developer.getSalary())
                .append("; Projects: ");
        developer.getProjects().forEach(builder::append);
        return builder.toString();
    }

    public static String format(Client client) {
        final StringBuilder builder = new StringBuilder();
        builder.append(client.getName())
                .append("; Phone: ")
                .append(client.getPhone())
                .append("; Email: ")
                .append(client.getEmail())
                .append("; Address: ")
                .append(client.getAddress())
                .append("; Organisation: ")
                .append(client.getOrganisation())
                .append("; Role: ")
                .append(client.getRole())
                .append("; Document: ")
                .append(client.getDocument())
                .append("; Projects: ");
        client.getProjects().forEach(builder::append);
        return builder.toString();
    }
}
