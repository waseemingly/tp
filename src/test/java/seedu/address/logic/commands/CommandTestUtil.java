package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.developer.Developer;
import seedu.address.model.developer.NameDeveloperContainsKeywordsPredicate;
import seedu.address.testutil.EditDeveloperDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ROLE_AMY = "Developer";
    public static final String VALID_ROLE_BOB = "Tester";
    public static final String VALID_SALARY_AMY = "5000";
    public static final String VALID_SALARY_BOB = "4000";
    public static final String VALID_DATEJOINED_AMY = "2020-01-01";
    public static final String VALID_DATEJOINED_BOB = "2020-02-01";
    public static final String VALID_GITHUBID_AMY = "amywalker";
    public static final String VALID_GITHUBID_BOB = "bobwalker";
    public static final String VALID_RATING_AMY = "5.0";
    public static final String VALID_RATING_BOB = "4.0";
    public static final String VALID_PROJECT_1_AMY = "ProjectA";
    public static final String VALID_PROJECT_2_AMY = "ProjectB";
    public static final String VALID_PROJECT_1_BOB = "ProjectC";
    public static final String VALID_PROJECT_2_BOB = "ProjectD";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String PROJECT_DESC_AMY = " " + PREFIX_PROJECT + VALID_PROJECT_1_AMY + VALID_PROJECT_2_AMY;
    public static final String PROJECT_DESC_BOB = " " + PREFIX_PROJECT + VALID_PROJECT_1_BOB + VALID_PROJECT_2_BOB;


    public static final String DATEJOINED_DESC_AMY = " " + PREFIX_DATEJOINED + VALID_DATEJOINED_AMY;
    public static final String DATEJOINED_DESC_BOB = " " + PREFIX_DATEJOINED + VALID_DATEJOINED_BOB;
    public static final String GITHUBID_DEC_AMY = " " + PREFIX_GITHUBID + VALID_GITHUBID_AMY;
    public static final String GITHUBID_DEC_BOB = " " + PREFIX_GITHUBID + VALID_GITHUBID_BOB;
    public static final String RATING_DEC_AMY = " " + PREFIX_RATING + VALID_RATING_AMY;
    public static final String RATING_DEC_BOB = " " + PREFIX_RATING + VALID_RATING_BOB;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE; //
    public static final String INVALID_SALARY_DESC = "abc" + PREFIX_SALARY; //
    public static final String INVALID_DATEJOINED_DESC = "2003-23" + PREFIX_DATEJOINED; //
    public static final String INVALID_GITHUBID_DESC = " " + PREFIX_GITHUBID; //
    public static final String INVALID_RATING_DESC = "abc" + PREFIX_RATING; //
    public static final String INVALID_PROJECT_DESC = " " + PREFIX_PROJECT; //

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditDeveloperCommand.EditDeveloperDescriptor DESC_AMY;
    public static final EditDeveloperCommand.EditDeveloperDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditDeveloperDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRole(VALID_ROLE_AMY).withSalary(VALID_SALARY_AMY).withDateJoined(VALID_DATEJOINED_AMY)
                .withGithubId(VALID_GITHUBID_AMY).withRating(VALID_RATING_AMY)
                .withProjects(VALID_PROJECT_1_AMY).build();
        DESC_BOB = new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRole(VALID_ROLE_BOB).withSalary(VALID_SALARY_BOB).withDateJoined(VALID_DATEJOINED_BOB)
                .withGithubId(VALID_GITHUBID_BOB).withRating(VALID_RATING_BOB)
                .withProjects(VALID_PROJECT_1_BOB, VALID_PROJECT_2_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false,
                false, TabIndex.Developer);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered developer list and selected developer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Developer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDeveloperList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredDeveloperList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the developer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showDeveloperAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDeveloperList().size());

        Developer developer = model.getFilteredDeveloperList().get(targetIndex.getZeroBased());
        final String[] splitName = developer.getName().fullName.split("\\s+");
        model.updateFilteredDeveloperList(new NameDeveloperContainsKeywordsPredicate(Collections
                .singletonList(splitName[0])));

        assertEquals(1, model.getFilteredDeveloperList().size());
    }

}
