package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Project objects.
 */
public class TypicalProjects {
    public static final Project PROJECT_A = new ProjectBuilder().withProjectName("Project A")
            .withProjectDescription("A softare engineering project").withProjectDeadlines(List
                    .of(new Deadline("09-12-2023,Design backend,HIGH,0", 1),
                        new Deadline("25-12-2023,Design frontend,MEDIUM,0", 2))).build();
    public static final Project PROJECT_B = new ProjectBuilder().withProjectName("Project B")
            .withProjectDescription("A project to create a juice ordering app").withProjectDeadlines(List
                    .of(new Deadline("09-12-2023,Design backend,HIGH,0", 1),
                        new Deadline("25-12-2023,Design frontend,MEDIUM,0", 2))).build();
    public static final Project PROJECT_C = new ProjectBuilder().withProjectName("Project C")
            .withProjectDescription("A project to create a juice ordering app").withProjectDeadlines(List
                    .of(new Deadline("09-12-2023,Design backend,HIGH,0", 1),
                            new Deadline("25-12-2023,Design frontend,MEDIUM,0", 2))).build();
    private TypicalProjects() {

    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical projects.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Project p : getTypicalProjects()) {
            ab.addProject(p);
        }
        return ab;
    }
    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C));
    }

}
