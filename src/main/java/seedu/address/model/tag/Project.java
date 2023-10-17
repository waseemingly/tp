package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidProjectName(String)}
 */
public class Project {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String projectName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param projectName A valid project name.
     */
    public Project(String projectName) {
        requireNonNull(projectName);
        checkArgument(isValidProjectName(projectName), MESSAGE_CONSTRAINTS);
        this.projectName = projectName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidProjectName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Project)) {
            return false;
        }

        Project otherTag = (Project) other;
        return projectName.equals(otherTag.projectName);
    }

    @Override
    public int hashCode() {
        return projectName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + projectName + ']';
    }

}
