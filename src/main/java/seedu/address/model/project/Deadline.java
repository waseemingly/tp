package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.commons.Date;

/**
 * Represents a Project's deadline in the address book.
 * Guarantees: immutable; fields are validated.
 */
public class Deadline {
    
    private final Date date;
    private final Description desc;
    private final Priority priority;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Date date, Description desc, Priority priority) {
        requireAllNonNull(date, desc);
        this.date = date;
        this.desc = desc;
        this.priority = priority;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Description getDescription() {
        return desc;
    }
    
    public Priority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return date.equals(otherDeadline.date)
                && desc.equals(otherDeadline.desc)
                && priority.equals(otherDeadline.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, desc, priority);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("date", date)
                .add("description", desc)
                .add("priority", priority)
                .toString();
    }
}
