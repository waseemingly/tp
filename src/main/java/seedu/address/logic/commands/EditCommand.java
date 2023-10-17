package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Project;

/**
 * Edits the details of an existing person in the address book.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "The details in the address book are already as given.";

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        DateJoined updatedDateJoined = editPersonDescriptor.getDateJoined().orElse(personToEdit.getDateJoined());
        Username updatedUsername = editPersonDescriptor.getUsername().orElse(personToEdit.getUsername());
        Password updatedPassword = editPersonDescriptor.getPassword().orElse(personToEdit.getPassword());
        Role updatedRole = editPersonDescriptor.getRole().orElse(personToEdit.getRole());
        Salary updatedSalary = editPersonDescriptor.getSalary().orElse(personToEdit.getSalary());
        Set<Project> updatedProjects = editPersonDescriptor.getProjects().orElse(personToEdit.getProjects());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedDateJoined,updatedUsername,updatedPassword,updatedRole,updatedSalary, updatedProjects);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Project> projects;
        private Username username;
        private Password password;
        private DateJoined dateJoined;
        private Role role;
        private Salary salary;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setProjects(toCopy.projects);
            setUsername(toCopy.username);
            setPassword(toCopy.password);
            setDateJoined(toCopy.dateJoined);
            setRole(toCopy.role);
            setSalary(toCopy.salary);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, email, address, projects, username, password, dateJoined, role, salary);
        }

        // Getter and setter methods for each field

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setProjects(Set<Project> projects) {
            this.projects = (projects != null) ? new HashSet<>(projects) : null;
        }

        public Optional<Set<Project>> getProjects() {
            return (projects != null) ? Optional.of(Collections.unmodifiableSet(projects)) : Optional.empty();
        }

        public void setUsername(Username username) {
            this.username = username;
        }

        public Optional<Username> getUsername() {
            return Optional.ofNullable(username);
        }

        public void setPassword(Password password) {
            this.password = password;
        }

        public Optional<Password> getPassword() {
            return Optional.ofNullable(password);
        }

        public void setDateJoined(DateJoined dateJoined) {
            this.dateJoined = dateJoined;
        }

        public Optional<DateJoined> getDateJoined() {
            return Optional.ofNullable(dateJoined);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(projects, otherEditPersonDescriptor.projects)
                    && Objects.equals(username, otherEditPersonDescriptor.username)
                    && Objects.equals(password, otherEditPersonDescriptor.password)
                    && Objects.equals(dateJoined, otherEditPersonDescriptor.dateJoined)
                    && Objects.equals(role, otherEditPersonDescriptor.role)
                    && Objects.equals(salary, otherEditPersonDescriptor.salary);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("projects", projects)
                    .add("username", username)
                    .add("password", password)
                    .add("dateJoined", dateJoined)
                    .add("role", role)
                    .add("salary", salary)
                    .toString();
        }
    }
}
