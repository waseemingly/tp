package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DeveloperNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A developer is considered unique by comparing using {@code Developer#isSamePerson(Developer)}. As such, adding and updating of
 * persons uses Developer#isSamePerson(Developer) for equality so as to ensure that the developer being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a developer uses Developer#equals(Object) so
 * as to ensure that the developer with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Developer#isSamePerson(Developer)
 */
public class UniquePersonList implements Iterable<Developer> {

    private final ObservableList<Developer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Developer> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent developer as the given argument.
     */
    public boolean contains(Developer toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a developer to the list.
     * The developer must not already exist in the list.
     */
    public void add(Developer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the developer {@code target} in the list with {@code editedDeveloper}.
     * {@code target} must exist in the list.
     * The developer identity of {@code editedDeveloper} must not be the same as another existing developer in the list.
     */
    public void setPerson(Developer target, Developer editedDeveloper) {
        requireAllNonNull(target, editedDeveloper);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DeveloperNotFoundException();
        }

        if (!target.isSamePerson(editedDeveloper) && contains(editedDeveloper)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDeveloper);
    }

    /**
     * Removes the equivalent developer from the list.
     * The developer must exist in the list.
     */
    public void remove(Developer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DeveloperNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code developers}.
     * {@code developers} must not contain duplicate developers.
     */
    public void setPersons(List<Developer> developers) {
        requireAllNonNull(developers);
        if (!personsAreUnique(developers)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(developers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Developer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Developer> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code developers} contains only unique developers.
     */
    private boolean personsAreUnique(List<Developer> developers) {
        for (int i = 0; i < developers.size() - 1; i++) {
            for (int j = i + 1; j < developers.size(); j++) {
                if (developers.get(i).isSamePerson(developers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
