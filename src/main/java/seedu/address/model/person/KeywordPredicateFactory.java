package seedu.address.model.person;

import java.util.List;

@FunctionalInterface
public interface KeywordPredicateFactory<T> {
    KeywordPredicate<T> createPredicate(List<String> keywords);
}
