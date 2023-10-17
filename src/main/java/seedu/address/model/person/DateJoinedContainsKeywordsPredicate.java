package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code DateJoined} matches any of the keywords given.
 */
public class DateJoinedContainsKeywordsPredicate implements KeywordPredicate<Person> {
    private final List<String> keywords;

    public DateJoinedContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getDateJoined().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateJoinedContainsKeywordsPredicate)) {
            return false;
        }

        DateJoinedContainsKeywordsPredicate otherDateJoinedContainsKeywordsPredicate = (DateJoinedContainsKeywordsPredicate) other;
        return keywords.equals(otherDateJoinedContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}