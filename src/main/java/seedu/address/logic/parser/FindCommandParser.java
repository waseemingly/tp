package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.DateJoinedContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.ProjectContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;
import seedu.address.model.person.UsernameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
        /**
         * Parses the given {@code String} of arguments in the context of the FindCommand
         * and returns a FindCommand object for execution.
         * @throws ParseException if the user input does not conform the expected format
         */
        public FindCommand parse(String args) throws ParseException {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            if (trimmedArgs.startsWith("n/")) {
                String[] nameKeywords = trimmedArgs.replaceFirst("n/", "").split("\\s+");
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else if (trimmedArgs.startsWith("r/")) {
                String[] roleKeywords = trimmedArgs.replaceFirst("r/", "").split("\\s+");
                return new FindCommand(new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywords)));
            } else if (trimmedArgs.startsWith("a/")) {
                String[] addressKeywords = trimmedArgs.replaceFirst("a/", "").split("\\s+");
                return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
            } else if (trimmedArgs.startsWith("d/")) {
                String[] dateJoinedKeywords = trimmedArgs.replaceFirst("d/", "").split("\\s+");
                return new FindCommand(new DateJoinedContainsKeywordsPredicate(Arrays.asList(dateJoinedKeywords)));
            } else if (trimmedArgs.startsWith("e/")) {
                String[] emailKeywords = trimmedArgs.replaceFirst("e/", "").split("\\s+");
                return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
            } else if (trimmedArgs.startsWith("ph/")) {
                String[] phoneKeywords = trimmedArgs.replaceFirst("p/", "").split("\\s+");
                return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
            } else if (trimmedArgs.startsWith("pr/")) {
                String[] phoneKeywords = trimmedArgs.replaceFirst("p/", "").split("\\s+");
                return new FindCommand(new ProjectContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
            } else if (trimmedArgs.startsWith("s/")) {
                String[] salaryKeywords = trimmedArgs.replaceFirst("s/", "").split("\\s+");
                return new FindCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(salaryKeywords)));
            } else if (trimmedArgs.startsWith("u/")) {
                String[] usernameKeywords = trimmedArgs.replaceFirst("u/", "").split("\\s+");
                return new FindCommand(new UsernameContainsKeywordsPredicate(Arrays.asList(usernameKeywords)));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

}