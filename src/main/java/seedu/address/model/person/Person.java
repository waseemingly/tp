package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.Name;
import seedu.address.model.project.Project;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    private final Username username;
    private final Password password;
    private final Role role;
    private final Salary salary;

    // Data fields
    private final Address address;
    private final DateJoined dateJoined;
    private final Set<Project> projects = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,DateJoined dateJoined, Username username,
                  Password password, Role role, Salary salary, Set<Project> projects){
        requireAllNonNull(name, phone, email, address, username, password, role, salary, projects);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateJoined = dateJoined;
        this.username = username;
        this.password = password;
        this.role = role;
        this.salary = salary;
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

    public Username getUsername() {
        return username;
    }
    public Password getPassword(){
        return password;
    }
    public Salary getSalary(){
        return salary;
    }
    public Role getRole(){
        return role;
    }

    public DateJoined getDateJoined(){
        return dateJoined;
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
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && dateJoined.equals(otherPerson.dateJoined)
                && username.equals(otherPerson.username)
                && password.equals(otherPerson.password)
                && salary.equals(otherPerson.salary)
                && role.equals(otherPerson.role)
                && projects.equals(otherPerson.projects);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, username, password, salary, role, projects);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("username", username)
                .add("password", password)
                .add("role", role)
                .add("salary", salary)
                .add("projects", projects)
                .toString();
    }

}
