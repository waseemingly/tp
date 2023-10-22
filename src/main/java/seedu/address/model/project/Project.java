package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.commons.Name;

/**
 * Represents a Project in the address book.
 * Guarantees: immutable; fields are validated.
 */
public class Project {

    private final Name projectName;
    private final Optional<Description> description;
    private final Set<Deadline> deadlines = new HashSet<>();

    /**
     * Constructs a {@code Tag}.
     *
     * @param projectName A valid project name.
     */
    public Project(Name projectName, Description desc, Set<Deadline> deadlines) {
        requireAllNonNull(projectName, desc, deadlines);
        this.projectName = projectName;
        this.description = Optional.ofNullable(desc);
        this.deadlines.addAll(deadlines);
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
        return projectName.equals(otherTag.projectName)
                && description.equals(otherTag.description)
                && deadlines.equals(otherTag.deadlines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, description, deadlines);
    }

    /**
     * Returns the name of the Project.
     */
    @Override
    public String toString() {
        return projectName.toString();
    }
    
    public Name getProjectName() {
        return projectName;
    }

    public Optional<Description> getProjectDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Deadline> getProjectDeadlines() {
        return Collections.unmodifiableSet(deadlines);
    }
}
