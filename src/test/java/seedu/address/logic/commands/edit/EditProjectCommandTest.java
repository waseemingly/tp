package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FULL_PROJECT_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_APPLEAPP;
import static seedu.address.logic.commands.CommandTestUtil.assertClientCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertProjectCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;
import seedu.address.testutil.EditProjectDescriptorBuilder;
import seedu.address.testutil.ProjectBuilder;

public class EditProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(editedProject).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProject = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastProject = model.getFilteredProjectList().get(indexLastProject.getZeroBased());

        ProjectBuilder projectInList = new ProjectBuilder(lastProject);
        Project editedProject = projectInList.withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP)
                .withDeadline(VALID_FULL_PROJECT_DEADLINE_1).build();

        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP)
                .withDeadlines(VALID_FULL_PROJECT_DEADLINE_1).build();
        EditProjectCommand editProjectCommand = new EditProjectCommand(indexLastProject, descriptor);

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProject(lastProject, editedProject);

        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
                new EditProjectCommand.EditProjectDescriptor());
        Project editedProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());

        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
                Messages.format(editedProject));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    
        assertProjectCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
//
//        Project projectInFilteredList = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
//        Project editedProject = new ProjectBuilder(projectInFilteredList).withName(VALID_NAME_PROJECT_2).build();
//        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
//                new EditProjectDescriptorBuilder().withName(VALID_NAME_PROJECT_2).build());
//
//        String expectedMessage = String.format(EditProjectCommand.MESSAGE_EDIT_PROJECT_SUCCESS,
//                Messages.format(editedProject));
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setProject(model.getFilteredProjectList().get(0), editedProject);
//
//        assertCommandSuccess(editProjectCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicateProjectUnfilteredList_failure() {
//        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
//        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder(firstProject).build();
//        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_SECOND_PROJECT, descriptor);
//
//        assertCommandFailure(editProjectCommand, model, EditProjectCommand.MESSAGE_DUPLICATE_PROJECT);
//    }
//
//    @Test
//    public void execute_duplicateProjectFilteredList_failure() {
//        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
//
//        // edit project in filtered list into a duplicate in address book
//        Project projectInList = model.getAddressBook().getProjectList().get(INDEX_SECOND_PROJECT.getZeroBased());
//        EditProjectCommand editProjectCommand = new EditProjectCommand(INDEX_FIRST_PROJECT,
//                new EditProjectDescriptorBuilder(projectInList).build());
//
//        assertCommandFailure(editProjectCommand, model, EditProjectCommand.MESSAGE_DUPLICATE_PROJECT);
//    }
//
//    @Test
//    public void execute_invalidProjectIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
//        EditProjectCommand.EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
//                .withName(VALID_NAME_PROJECT_2).build();
//        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidProjectIndexFilteredList_failure() {
//        showProjectAtIndex(model, INDEX_FIRST_PROJECT);
//        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getProjectList().size());
//
//        EditProjectCommand editProjectCommand = new EditProjectCommand(outOfBoundIndex,
//                new EditProjectDescriptorBuilder().withName(VALID_NAME_PROJECT_2).build());
//
//        assertCommandFailure(editProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditProjectCommand standardCommand = new EditProjectCommand(INDEX_FIRST_PROJECT, DESCRIPTION_PROJECT_1);
//
//        // same values -> returns true
//        EditProjectCommand.EditProjectDescriptor copyDescriptor = new EditProjectCommand.EditProjectDescriptor(
//                DESCRIPTION_PROJECT_1);
//        EditProjectCommand commandWithSameValues = new EditProjectCommand(INDEX_FIRST_PROJECT, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_SECOND_PROJECT, DESCRIPTION_PROJECT_1)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditProjectCommand(INDEX_FIRST_PROJECT, DESCRIPTION_PROJECT_2)));
//    }
//
//    @Test
//    public void toStringMethod() {
//        Index index = Index.fromOneBased(1);
//        EditProjectCommand.EditProjectDescriptor editProjectDescriptor = new EditProjectCommand.EditProjectDescriptor();
//        EditProjectCommand editProjectCommand = new EditProjectCommand(index, editProjectDescriptor);
//        String expected = EditProjectCommand.class.getCanonicalName() + "{index=" + index
//                + ", editProjectDescriptor=" + editProjectDescriptor + "}";
//        assertEquals(expected, editProjectCommand.toString());
//    }
//
//    /**
//     * Updates {@code model}'s filtered list to show no one.
//     */
//    private void showNoProject(Model model) {
//        model.updateFilteredProjectList(p -> false);
//
//        assertTrue(model.getFilteredProjectList().isEmpty());
//    }
//
//    /**
//     * Updates {@code model}'s filtered list to show projects at {@code targetIndex} in {@code model}'s address book.
//     */
//    private void showProjectAtIndex(Model model, Index targetIndex) {
//        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());
//
//        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
//        final String[] splitName = project.getProjectName().fullName.split("\\s+");
//        model.updateFilteredProjectList(
//                p -> Arrays.asList(splitName).contains(p.getProjectName().fullName));
//
//        assertEquals(1, model.getFilteredProjectList().size());
//    }
}
