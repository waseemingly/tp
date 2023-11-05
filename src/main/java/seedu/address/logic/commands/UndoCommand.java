package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command to undo the most recent change in the application.
 * This allows users to revert the effects of the previous command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful! The change below has been undone: ";

    /**
     * Executes the undo operation by restoring the previous state of the model and
     * retrieving information about the undone command.
     *
     * @param model The model from which to undo the most recent change.
     * @return A CommandResult indicating the success of the undo operation and the
     *         details of the undone command.
     * @throws CommandException If an error occurs during the undo operation.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undoAddressBook(model);
        String previousCommand = model.getPreviousCommand();
        TabIndex index = model.getPreviousTabIndex();
        return new CommandResult(MESSAGE_SUCCESS + "\n" + previousCommand, index);
    }
}
