package seedu.address.model.person;

import java.util.function.Predicate;

public interface KeywordPredicate<T extends Person> extends Predicate<T> {
    // can define common methods if needed.
    // For now, this interface will be empty and serve mainly as a type.
}
