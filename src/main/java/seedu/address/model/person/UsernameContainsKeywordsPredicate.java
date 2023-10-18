package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Username} matches any of the keywords given.
 */
public class UsernameContainsKeywordsPredicate implements KeywordPredicate<Person> {
    private final List<String> keywords;

    public UsernameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getUsername().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UsernameContainsKeywordsPredicate)) {
            return false;
        }

        UsernameContainsKeywordsPredicate otherUsernameContainsKeywordsPredicate = (UsernameContainsKeywordsPredicate) other;
        return keywords.equals(otherUsernameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}