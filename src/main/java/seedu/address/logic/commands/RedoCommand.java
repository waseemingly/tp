package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
/**
 * Represents a command to redo the most recent undone command in the address book.
 * It restores the state of the address book to the state before the last undo operation.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful! The change below has been redone:";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String previousCommand = model.getPreviousCommand();
        TabIndex index = model.getPreviousTabIndex();
        model.redoAddressBook(model);
        return new CommandResult(MESSAGE_SUCCESS + "\n" + previousCommand, index);
    }
}
