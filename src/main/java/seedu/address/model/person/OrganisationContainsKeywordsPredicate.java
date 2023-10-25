package seedu.address.model.person;

import java.util.List;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Client.Client;

/**
 * Tests that a {@code Client}'s {@code Organisation} matches any of the keywords given.
 */
public class OrganisationContainsKeywordsPredicate implements KeywordPredicate<Client> {
    private final List<String> keywords;

    public OrganisationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        // Assuming that the Person class (which can be a Client) has a getOrganisation() method that returns a String representation of the organisation.
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
