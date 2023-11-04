package seedu.address.model.client;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.KeywordPredicate;

import java.util.List;

/**
 * Tests that a {@code Developer}'s {@code Organisation} matches any of the keywords given.
 */
public class OrganisationContainsKeywordsPredicate implements KeywordPredicate<Client> {
    private final List<String> keywords;

    public OrganisationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getOrganisation().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrganisationContainsKeywordsPredicate)) {
            return false;
        }

        OrganisationContainsKeywordsPredicate otherOrganisationContainsKeywordsPredicate = (OrganisationContainsKeywordsPredicate) other;
        return keywords.equals(otherOrganisationContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
