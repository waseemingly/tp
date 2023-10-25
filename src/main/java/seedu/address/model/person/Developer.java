package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.commons.Name;
import seedu.address.model.project.Project;

/**
 * Represents a Developer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Developer {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    private final Role role;

    // Data fields
    private final Address address;
    private final Set<Project> projects = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Developer (Name name, Phone phone, Email email, Address address, Role role, Set<Project> projects) {
        requireAllNonNull(name, phone, email, address, role, projects);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.projects.addAll(projects);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Role getRole(){
        return role;
    }



    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Project> getProjects() {
        return Collections.unmodifiableSet(projects);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Developer otherDeveloper) {
        if (otherDeveloper == this) {
            return true;
        }

        return otherDeveloper != null
                && otherDeveloper.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Developer)) {
            return false;
        }

        Developer otherDeveloper = (Developer) other;
        return name.equals(otherDeveloper.name)
                && phone.equals(otherDeveloper.phone)
                && email.equals(otherDeveloper.email)
                && address.equals(otherDeveloper.address)
                && role.equals(otherDeveloper.role)
                && projects.equals(otherDeveloper.projects);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, role, projects);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("role", role)
                .add("projects", projects)
                .toString();
    }

}
