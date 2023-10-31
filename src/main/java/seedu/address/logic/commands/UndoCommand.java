package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful!";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undoAddressBook(model);
        return new CommandResult(MESSAGE_SUCCESS, TabIndex.Developer);
    }
}
