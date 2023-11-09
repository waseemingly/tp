package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_1_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDeveloperAtIndex;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.developer.Developer;
import seedu.address.testutil.DeveloperBuilder;
import seedu.address.testutil.EditDeveloperDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditDeveloperCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        //
        Developer editedDeveloper = new DeveloperBuilder().build();
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(editedDeveloper)
                .build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(model.getFilteredDeveloperList().get(0), editedDeveloper);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        //Fix Errors
        Index indexLastPerson = Index.fromOneBased(model.getFilteredDeveloperList().size());
        Developer lastDeveloper = model.getFilteredDeveloperList().get(indexLastPerson.getZeroBased());

        DeveloperBuilder personInList = new DeveloperBuilder(lastDeveloper);
        Developer editedDeveloper = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withProjects(VALID_PROJECT_1_BOB).build();

        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withProjects(VALID_PROJECT_1_BOB).build();
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(lastDeveloper, editedDeveloper);

        assertCommandSuccess(editDeveloperCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperCommand.EditDeveloperDescriptor());
        Developer editedDeveloper = model.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editDeveloperCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDeveloperAtIndex(model, INDEX_FIRST_PERSON);

        Developer developerInFilteredList = model.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());
        Developer editedDeveloper = new DeveloperBuilder(developerInFilteredList).withName(VALID_NAME_BOB).build();
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(model.getFilteredDeveloperList().get(0), editedDeveloper);

        assertCommandSuccess(editDeveloperCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Developer firstDeveloper = model.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder(firstDeveloper)
                .build();
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editDeveloperCommand, model, EditDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showDeveloperAtIndex(model, INDEX_FIRST_PERSON);

        // edit developer in filtered list into a duplicate in address book
        Developer developerInList = model.getAddressBook().getDeveloperList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder(developerInList).build());

        assertCommandFailure(editDeveloperCommand, model, EditDeveloperCommand.MESSAGE_DUPLICATE_DEVELOPER);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeveloperList().size() + 1);
        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editDeveloperCommand, model, Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showDeveloperAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDeveloperList().size());

        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(outOfBoundIndex,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editDeveloperCommand, model, Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDeveloperCommand standardCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditDeveloperCommand.EditDeveloperDescriptor copyDescriptor =
                new EditDeveloperCommand.EditDeveloperDescriptor(DESC_AMY);
        EditDeveloperCommand commandWithSameValues = new EditDeveloperCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditDeveloperCommand(INDEX_SECOND_PERSON, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditDeveloperCommand(INDEX_FIRST_PERSON, DESC_BOB));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditDeveloperCommand.EditDeveloperDescriptor editDevelopeDescriptor =
                new EditDeveloperCommand.EditDeveloperDescriptor();
        EditDeveloperCommand editDeveloperCommand = new EditDeveloperCommand(index, editDevelopeDescriptor);
        String expected = EditDeveloperCommand.class.getCanonicalName() + "{index=" + index
                + ", editDevelopeDescriptor="
                + editDevelopeDescriptor + "}";
        assertEquals(expected, editDeveloperCommand.toString());
    }

}
