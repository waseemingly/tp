package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.DateJoinedContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.KeywordPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Developer;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.ProjectContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.person.SalaryContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Please Find with the correct input "
            + "Find pr/<Project Name> OR Find r/<Role> OR Find n/<Name>.\n"
            + "Example: " + COMMAND_WORD + " n/ alice bob charlie";

    private KeywordPredicate<Developer> predicate;

    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this.predicate = namePredicate;
    }

    public FindCommand(RoleContainsKeywordsPredicate rolePredicate) {
        this.predicate = rolePredicate;
    }

    public FindCommand(AddressContainsKeywordsPredicate addressPredicate) {
        this.predicate = addressPredicate;
    }

    public FindCommand(DateJoinedContainsKeywordsPredicate dateJoinedPredicate) {
        this.predicate = dateJoinedPredicate;
    }

    public FindCommand(EmailContainsKeywordsPredicate emailPredicate) {
        this.predicate = emailPredicate;
    }

    public FindCommand(PhoneContainsKeywordsPredicate phonePredicate) {
        this.predicate = phonePredicate;
    }

    public FindCommand(SalaryContainsKeywordsPredicate salaryPredicate) {
        this.predicate = salaryPredicate;
    }


    public FindCommand(ProjectContainsKeywordsPredicate projectPredicate) {
        this.predicate = projectPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
