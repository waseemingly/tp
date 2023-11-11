package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProjects.PROJECT_A;
import static seedu.address.testutil.TypicalProjects.PROJECT_B;
import static seedu.address.testutil.TypicalProjects.PROJECT_C;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindProjectCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProjectCommand}.
 */
public class FindProjectCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ProjectNameContainsKeywordsPredicate firstPredicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ProjectNameContainsKeywordsPredicate secondPredicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindProjectCommand findFirstCommand = new FindProjectCommand(firstPredicate);
        FindProjectCommand findSecondCommand = new FindProjectCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindProjectCommand findFirstCommandCopy = new FindProjectCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different project -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noProjectFound() {
        String expectedMessage = "There are no projects with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("hii");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProjectList());
    }

    @Test
    public void execute_multipleKeywords_multipleProjectsFound() {
        String expectedMessage = "These are the 3 projects with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("Project A B C");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C), model.getFilteredProjectList());
    }

    @Test
    public void toStringMethod() {
        ProjectNameContainsKeywordsPredicate predicate =
                new ProjectNameContainsKeywordsPredicate(List.of("keyword"));
        FindProjectCommand findProjectCommand = new FindProjectCommand(predicate);
        String expected = FindProjectCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findProjectCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code ProjectNameContainsKeywordsPredicate}.
     */
    private ProjectNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ProjectNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
