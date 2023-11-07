package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.Set;

import seedu.address.logic.commands.add.AddDeveloperCommand;
import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.model.developer.Developer;

/**
 * A utility class for Developer.
 */
public class DeveloperUtil {

    /**
     * Returns an add command string for adding the {@code developer}.
     */
    public static String getAddCommand(Developer developer) {
        return AddDeveloperCommand.COMMAND_WORD + " " + getDeveloperDetails(developer);
    }

    /**
     * Returns the part of command string for the given {@code developer}'s details.
     */
    public static String getDeveloperDetails(Developer developer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + developer.getName().fullName + " ");
        sb.append(PREFIX_PHONE + developer.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + developer.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + developer.getAddress().value + " ");
        developer.getProjects().stream().forEach(
                s -> sb.append(PREFIX_PROJECT + s + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditDeveloperCommand.EditDeveloperDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getProjects().isPresent()) {
            Set<String> project = descriptor.getProjects().get();
            if (project.isEmpty()) {
                sb.append(PREFIX_PROJECT);
            } else {
                project.forEach(s -> sb.append(PREFIX_PROJECT).append(s).append(" "));
            }
        }
        return sb.toString();
    }
}
