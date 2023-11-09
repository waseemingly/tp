package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindDeveloperCommand;
import seedu.address.logic.parser.find.FindDeveloperCommandParser;
import seedu.address.model.developer.NameDeveloperContainsKeywordsPredicate;

public class FindDeveloperCommandParserTest {

    private final FindDeveloperCommandParser parser = new FindDeveloperCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindDeveloperCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindDeveloperCommand() {
        // no leading and trailing whitespaces
        FindDeveloperCommand expectedFindDeveloperCommand =
                new FindDeveloperCommand(new NameDeveloperContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindDeveloperCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindDeveloperCommand);
    }

}
