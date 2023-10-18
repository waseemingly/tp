package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEJOINED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditOthersCommand;
import seedu.address.logic.commands.EditSelfCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Project;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    
    /** An ArrayList of restricted fields represented by Prefixes that should not be edited given current user. */
    private static final ArrayList<Prefix> restrictedPrefix = new ArrayList<>();

    /**
     * The role of the current user.
     */
    private static Role role;
    
    /**
     * Adds each restricted field as a Prefix to the restrictedPrefix ArrayList.
     */
    private static void setRestrictedPrefixes(Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            restrictedPrefix.add(prefix);
        }
    }

    /**
     * Sets role to the role of the current user.
     *
     * @param userRole The role of the current user.
     */
    public static void setRole(Role userRole) {
        role = userRole;
        
        if (userRole.equals(new Role("Developer"))) {
            setRestrictedPrefixes(PREFIX_PROJECT, PREFIX_DATEJOINED, PREFIX_ROLE, PREFIX_SALARY);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */

    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_PROJECT, PREFIX_DATEJOINED, PREFIX_ROLE, PREFIX_SALARY, PREFIX_USERNAME,
                PREFIX_PASSWORD);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_PROJECT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_PROJECT, PREFIX_DATEJOINED, PREFIX_ROLE, PREFIX_SALARY, PREFIX_USERNAME, PREFIX_PASSWORD);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        for (Prefix prefix : restrictedPrefix) {
            if (argMultimap.getValue(prefix).isPresent()) {
               throw new ParseException(Messages.MESSAGE_UNAUTHORISED_COMMAND); 
            }
        }
        
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATEJOINED).isPresent()) {
            editPersonDescriptor.setDateJoined(ParserUtil.parseDateJoined(argMultimap.getValue(PREFIX_DATEJOINED).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editPersonDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editPersonDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_USERNAME).isPresent()) {
            editPersonDescriptor.setUsername(ParserUtil.parseUsername(argMultimap.getValue(PREFIX_USERNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PASSWORD).isPresent()) {
            editPersonDescriptor.setPassword(ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_PROJECT)).ifPresent(editPersonDescriptor::setProjects);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        EditCommand editCommand;

        String preamble = argMultimap.getPreamble();
        
        if (role.equals(new Role("HR"))) {
            if (ParserUtil.canParseIndex(preamble)) {
                Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
                editCommand = new EditOthersCommand(index, editPersonDescriptor);
            } else {
                editCommand = new EditSelfCommand(editPersonDescriptor);
            }
        } else {
            if (ParserUtil.canParseIndex(preamble)) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_UNAUTHORISED_COMMAND, EditSelfCommand.MESSAGE_USAGE));
            } else {
                editCommand = new EditSelfCommand(editPersonDescriptor);
            }
        }
        
        return editCommand;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Project>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseProjects(tagSet));
    }

}
