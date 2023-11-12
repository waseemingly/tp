package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CALEB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CALEB;
import static seedu.address.logic.commands.CommandTestUtil.assertClientCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditClientCommand.
 */
public class EditClientCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertClientCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    // Project needs to be created
    /*@Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDeveloper = Index.fromOneBased(model.getFilteredDeveloperList().size());
        Developer lastDeveloper = model.getFilteredDeveloperList().get(indexLastDeveloper.getZeroBased());

        DeveloperBuilder developerInList = new DeveloperBuilder(lastDeveloper);
        Developer editedDeveloper = developerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withProjects(VALID_PROJECT_1_BOB).build();

        EditDeveloperCommand.EditDeveloperDescriptor descriptor = new EditDeveloperDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withProjects(VALID_PROJECT_1_BOB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(indexLastDeveloper, descriptor);

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(lastDeveloper, editedDeveloper);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }*/

    // Project needs to be created
    /*
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperCommand.EditDeveloperDescriptor());
        Developer editedDeveloper = model.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }*/

    // Project needs to be created
    /* @Test
    public void execute_filteredList_success() {
        showDeveloperAtIndex(model, INDEX_FIRST_PERSON);

        Developer developerInFilteredList = model.getFilteredDeveloperList().get(INDEX_FIRST_PERSON.getZeroBased());
        Developer editedDeveloper = new DeveloperBuilder(developerInFilteredList).withName(VALID_NAME_BOB).build();
        EditDeveloperCommand editCommand = new EditDeveloperCommand(INDEX_FIRST_PERSON,
                new EditDeveloperDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditDeveloperCommand.MESSAGE_EDIT_DEVELOPER_SUCCESS,
                Messages.format(editedDeveloper));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDeveloper(model.getFilteredDeveloperList().get(0), editedDeveloper);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }*/

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient)
                .build();
        EditClientCommand editCommand = new EditClientCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditClientCommand editCommand = new EditClientCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_CALEB).build();
        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getClientList().size());

        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(INDEX_FIRST_PERSON, DESC_CALEB);

        // same values -> returns true
        EditClientCommand.EditClientDescriptor copyDescriptor = new EditClientCommand.EditClientDescriptor(DESC_CALEB);
        EditClientCommand commandWithSameValues = new EditClientCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        Assertions.assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditClientCommand(INDEX_SECOND_PERSON, DESC_CALEB));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditClientCommand(INDEX_FIRST_PERSON, DESC_DAN));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditClientCommand.EditClientDescriptor editClientDescriptor = new EditClientCommand
                .EditClientDescriptor();
        EditClientCommand editCommand = new EditClientCommand(index, editClientDescriptor);
        String expected = EditClientCommand.class.getCanonicalName() + "{index=" + index
                + ", editClientDescriptor="
                + editClientDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }
}
