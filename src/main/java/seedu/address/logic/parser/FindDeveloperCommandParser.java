package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindDeveloperCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.developer.DateJoinedContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.ProjectContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.developer.SalaryContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindDeveloperCommand object
 */
public class FindDeveloperCommandParser implements Parser<FindDeveloperCommand> {
        /**
         * Parses the given {@code String} of arguments in the context of the FindDeveloperCommand
         * and returns a FindDeveloperCommand object for execution.
         * @throws ParseException if the user input does not conform the expected format
         */
        public FindDeveloperCommand parse(String args) throws ParseException {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeveloperCommand.MESSAGE_USAGE));
            }

            if (trimmedArgs.startsWith("developer n/")) {
                String[] nameKeywords = trimmedArgs.replaceFirst("developer n/", "").split("\\s+");
                return new FindDeveloperCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else if (trimmedArgs.startsWith("developer r/")) {
                String[] roleKeywords = trimmedArgs.replaceFirst("developer r/", "").split("\\s+");
                return new FindDeveloperCommand(new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywords)));
            } else if (trimmedArgs.startsWith("developer a/")) {
                String[] addressKeywords = trimmedArgs.replaceFirst("developer a/", "").split("\\s+");
                return new FindDeveloperCommand(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
            } else if (trimmedArgs.startsWith("developer d/")) {
                String[] dateJoinedKeywords = trimmedArgs.replaceFirst("developer d/", "").split("\\s+");
                return new FindDeveloperCommand(new DateJoinedContainsKeywordsPredicate(Arrays.asList(dateJoinedKeywords)));
            } else if (trimmedArgs.startsWith("developer e/")) {
                String[] emailKeywords = trimmedArgs.replaceFirst("developer e/", "").split("\\s+");
                return new FindDeveloperCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
            } else if (trimmedArgs.startsWith("developer ph/")) {
                String[] phoneKeywords = trimmedArgs.replaceFirst("developer ph/", "").split("\\s+");
                return new FindDeveloperCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
            } else if (trimmedArgs.startsWith("developer pr/")) {
                String[] projectKeywords = trimmedArgs.replaceFirst("developer pr/", "").split("\\s+");
                return new FindDeveloperCommand(new ProjectContainsKeywordsPredicate(Arrays.asList(projectKeywords)));
            } else if (trimmedArgs.startsWith("developer s/")) {
                String[] salaryKeywords = trimmedArgs.replaceFirst("developer s/", "").split("\\s+");
                return new FindDeveloperCommand(new SalaryContainsKeywordsPredicate(Arrays.asList(salaryKeywords)));
            } else if (trimmedArgs.startsWith("client n/")) {
                String[] nameKeywords = trimmedArgs.replaceFirst("client n/", "").split("\\s+");
                return new FindDeveloperCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else if (trimmedArgs.startsWith("client ph/")) {
                String[] phoneKeywords = trimmedArgs.replaceFirst("client ph/", "").split("\\s+");
                return new FindDeveloperCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
            } else if (trimmedArgs.startsWith("client e/")) {
                String[] emailKeywords = trimmedArgs.replaceFirst("client e/", "").split("\\s+");
                return new FindDeveloperCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
            } else if (trimmedArgs.startsWith("client a/")) {
                String[] addressKeywords = trimmedArgs.replaceFirst("client a/", "").split("\\s+");
                return new FindDeveloperCommand(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
            } else if (trimmedArgs.startsWith("client r/")) {
                String[] roleKeywords = trimmedArgs.replaceFirst("client r/", "").split("\\s+");
                return new FindDeveloperCommand(new RoleContainsKeywordsPredicate(Arrays.asList(roleKeywords)));
            } else if (trimmedArgs.startsWith("client pr/")) {
                String[] projectKeywords = trimmedArgs.replaceFirst("client pr/", "").split("\\s+");
                return new FindDeveloperCommand(new ProjectContainsKeywordsPredicate(Arrays.asList(projectKeywords)));
            }
//            else if (trimmedArgs.startsWith("client org/")) {
//                String[] organisationKeywords = trimmedArgs.replaceFirst("client org/", "").split("\\s+");
//                return new FindDeveloperCommand(new OrganisationContainsKeywordsPredicate(Arrays.asList(organisationKeywords)));
//            } else if (trimmedArgs.startsWith("client doc/")) {
//                String[] documentKeywords = trimmedArgs.replaceFirst("client doc/", "").split("\\s+");
//                return new FindDeveloperCommand(new DocumentContainsKeywordsPredicate(Arrays.asList(documentKeywords)));
//            }
            else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDeveloperCommand.MESSAGE_USAGE));
            }
        }

}