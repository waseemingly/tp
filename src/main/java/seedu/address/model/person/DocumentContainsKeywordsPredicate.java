package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Client.Client;

/**
 * Tests that a {@code Client}'s {@code Document} matches any of the keywords given.
 */
public class DocumentContainsKeywordsPredicate implements KeywordPredicate<Client> {
    private final List<String> keywords;

    public DocumentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        // Assuming that the Person class (which can be a Client) has a getDocument() method that returns a String representation of the document.
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getDocument().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DocumentContainsKeywordsPredicate)) {
            return false;
        }

        DocumentContainsKeywordsPredicate otherDocumentContainsKeywordsPredicate = (DocumentContainsKeywordsPredicate) other;
        return keywords.equals(otherDocumentContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
