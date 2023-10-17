package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class EditSelfCommand extends EditCommand {
    
    /** The current user which will be edited. */
    private static Person currentUser;
    
    private final EditPersonDescriptor editPersonDescriptor;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits your details. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_USERNAME + "USERNAME]"
            + "[" + PREFIX_PASSWORD + "PASSWORD]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    
    /**
     * @param editPersonDescriptor details to edit the current user with
     */
    public EditSelfCommand(EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(editPersonDescriptor);
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Sets currentUser to user.
     * 
     * @param user The current user that is logged in.
     */
    public static void setCurrentUser(Person user) {
        currentUser = user;
    }
    
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        
        Person personToEdit = currentUser;
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(EditCommand.MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSelfCommand)) {
            return false;
        }

        EditSelfCommand otherEditCommand = (EditSelfCommand) other;
        return editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }
}
