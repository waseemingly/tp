package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindClientCommand;
import seedu.address.logic.commands.UnlockCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Password;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

public class UnlockCommandParser implements Parser<UnlockCommand> {
    @Override
    public UnlockCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PASSWORD);

        if (argMultimap.getValue(PREFIX_PASSWORD).isPresent()) {
            String input = ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get());
            return new UnlockCommand(input);
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlockCommand.MESSAGE_USAGE));
    }
}
