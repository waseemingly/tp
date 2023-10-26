package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.developer.Developer;

/**
 * Tests that a {@code Developer}'s {@code Role} matches any of the keywords given.
 */
public class RoleContainsKeywordsPredicate implements KeywordPredicate<Developer> {
    private final List<String> keywords;

    public RoleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Developer developer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(developer.getRole().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoleContainsKeywordsPredicate)) {
            return false;
        }

        RoleContainsKeywordsPredicate otherRoleContainsKeywordsPredicate = (RoleContainsKeywordsPredicate) other;
        return keywords.equals(otherRoleContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}