package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's description in the address book.
 * Guarantees: immutable; is validated.
 */
public class Description {
    
    public final String desc;

    /**
     * Constructs a {@code Description}.
     *
     * @param desc The String description.
     */
    public Description(String desc) {
        requireNonNull(desc);
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDesc = (Description) other;
        return desc.equals(otherDesc.desc);
    }

    @Override
    public int hashCode() {
        return desc.hashCode();
    }

}
