package seedu.address.testutil;

import java.util.List;

import seedu.address.model.commons.Name;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {
    public static final String DEFAULT_PROJECT_NAME = "JuiceApp";
    public static final String DEFAULT_DESCRIPTION = "A project to create a juice ordering app";
    public static final List<Deadline> DEFAULT_DEADLINES = List.of(new Deadline("09-12-2023,Design backend,HIGH,0",
                    1),
            new Deadline("25-12-2023,Design frontend,MEDIUM,0", 2));
    private Name projectName = new Name(DEFAULT_PROJECT_NAME);
    private Description description;
    private List<Deadline> deadlines;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        this.projectName = new Name(DEFAULT_PROJECT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        deadlines = DEFAULT_DEADLINES;
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project p) {
        this.projectName = p.getProjectName();
        this.description = p.getProjectDescription();
        this.deadlines = p.getProjectDeadlines();
    }

    /**
     * Sets the {@code Name} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectName(String projectName) {
        this.projectName = new Name(projectName);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Deadlines} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDeadlines(List<Deadline> deadlines) {
        this.deadlines = deadlines;
        return this;
    }

    /**
     * Builds the project.
     */
    public Project build() {
        return new Project(projectName, description, deadlines);
    }


}
