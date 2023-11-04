package seedu.address.model.project;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.commons.Name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Represents a Project in the address book.
 * Guarantees: immutable; fields are validated.
 */
public class Project {

    public static final Prefix[] unusedPrefixes = new Prefix[]{PREFIX_DATEJOINED, PREFIX_SALARY, PREFIX_RATING,
            PREFIX_GITHUBID, PREFIX_ADDRESS, PREFIX_DOCUMENT, PREFIX_EMAIL, PREFIX_ORGANISATION, PREFIX_PHONE,
            PREFIX_PROJECT, PREFIX_ROLE};
    public static final Prefix[] unusedPrefixesForEdit = new Prefix[]{PREFIX_DATEJOINED, PREFIX_SALARY, PREFIX_RATING,
            PREFIX_GITHUBID, PREFIX_ADDRESS, PREFIX_DOCUMENT, PREFIX_EMAIL, PREFIX_ORGANISATION, PREFIX_PHONE,
            PREFIX_PROJECT, PREFIX_ROLE, PREFIX_NAME};
    private final Name projectName;
    private final Description description;
    private final List<Deadline> deadlines;
    private final FilteredList<Deadline> filteredDeadlines;

    /**
     * Constructs a {@code Tag}.
     *
     * @param projectName A valid project name.
     */
    public Project(Name projectName, Description desc, List<Deadline> deadlines) {
        requireAllNonNull(projectName, desc, deadlines);
        this.projectName = projectName;
        this.description = desc;
        this.deadlines = deadlines;
        this.filteredDeadlines = new FilteredList<>(FXCollections.observableList(deadlines));
    }

    public Project(String projectName) {
        this(new Name(projectName), new Description(""), new ArrayList<>());
    }

    public void setPredicate(Predicate<Deadline> predicate) {
        filteredDeadlines.setPredicate(predicate);
    }

    public String getName() {
        return projectName.fullName;
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

    public Description getProjectDescription() {
        return description;
    }

    /**
     * Returns a list with each element being the String representation of the respective deadline.
     * The element at the given index is the String representation of the respective deadline such that it is completed.
     *
     * @param index The index of the deadline to mark as completed.
     * @return A list containing String representations of deadlines.
     */
    public List<String> markDeadlineStringRep(int index) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < deadlines.size(); i++) {
            if (i == index) {
                res.add(deadlines.get(i).getDoneStringRepresentation());
            } else {
                res.add(deadlines.get(i).getStringRepresentation());
            }
        }
        return res;
    }

    /**
     * Returns a list with each element being the String representation of the respective deadline.
     * The element at the given index is the String representation of the respective deadline such that it is incomplete.
     *
     * @param index The index of the deadline to mark as incomplete.
     * @return A list containing String representations of deadlines.
     */
    public List<String> unmarkDeadlineStringRep(int index) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < deadlines.size(); i++) {
            if (i == index) {
                res.add(deadlines.get(i).getUndoneStringRepresentation());
            } else {
                res.add(deadlines.get(i).getStringRepresentation());
            }
        }
        return res;
    }

    /**
     * Returns the size of the deadlines list.
     *
     * @return An integer representing the size of the deadlines list.
     */
    public int deadlineListSize() {
        return deadlines.size();
    }

    /**
     * Returns an immutable list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Deadline> getProjectDeadlines() {
        return Collections.unmodifiableList(deadlines);
    }

    public FilteredList<Deadline> getProjectFilteredDeadlines() {
        return filteredDeadlines;
    }

    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName());
    }

    public boolean isSameProject(String projectName) {
        return projectName.equals(getName());
    }
}
