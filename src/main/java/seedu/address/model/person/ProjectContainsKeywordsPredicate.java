package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.developer.Developer;

/**
 * Tests that a {@code Developer}'s {@code Project} matches any of the keywords given.
 */
public class ProjectContainsKeywordsPredicate implements KeywordPredicate<Developer> {
    private final List<String> keywords;

    public ProjectContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Developer developer) {
        // Assuming that the Developer class has a getProject() method that returns a Project object
        // and that Project object has a method called asString() which returns the string representation of the project.
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(developer.getProjects().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProjectContainsKeywordsPredicate)) {
            return false;
        }

        ProjectContainsKeywordsPredicate otherProjectContainsKeywordsPredicate = (ProjectContainsKeywordsPredicate) other;
        return keywords.equals(otherProjectContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}