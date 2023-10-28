package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.Role;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.*;
import seedu.address.model.project.Project;

/**
 * Represents an edit command.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Developer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "The details in the address book are already as given.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the developer identified "
            + "by the index number used in the displayed developer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PROJECT + "PROJECT]"
            + "[" + PREFIX_DATEJOINED + "DATE JOINED]"
            + "[" + PREFIX_ROLE + "ROLE]"
            + "[" + PREFIX_SALARY + "SALARY]"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    private final Index index;

    private final EditPersonDescriptor editPersonDescriptor;


    /**
     * @param index of the developer in the filtered developer list to edit
     * @param editPersonDescriptor details to edit the developer with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    /**
     * Creates and returns a {@code Developer} with the details of {@code developerToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    static Developer createEditedPerson(Developer developerToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert developerToEdit != null;

        /*Name updatedName = editPersonDescriptor.getName().orElse(developerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(developerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(developerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(developerToEdit.getAddress());
        Date updatedDateJoined = editPersonDescriptor.getDateJoined().orElse(developerToEdit.getDateJoined());
        Role updatedRole = editPersonDescriptor.getRole().orElse(developerToEdit.getRole());
        Salary updatedSalary = editPersonDescriptor.getSalary().orElse(developerToEdit.getSalary());
        Set<Project> updatedProjects = editPersonDescriptor.getProjects().orElse(developerToEdit.getProjects());

        return new Developer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedDateJoined,updatedRole,updatedSalary, updatedProjects);*/
        return null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        /*List<Developer> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Developer developerToEdit = lastShownList.get(index.getZeroBased());
        Developer editedDeveloper = createEditedPerson(developerToEdit, editPersonDescriptor);

        if (!developerToEdit.isSamePerson(editedDeveloper) && model.hasPerson(editedDeveloper)) {
            throw new CommandException(EditCommand.MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(developerToEdit, editedDeveloper);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedDeveloper)));
*/
        return null;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the developer with. Each non-empty field value will replace the
     * corresponding field value of the developer.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Project> projects;
        private Date dateJoined;
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
            setDateJoined(toCopy.dateJoined);
            setRole(toCopy.role);
            setSalary(toCopy.salary);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, email, address, projects, dateJoined, role, salary);
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


        public void setDateJoined(Date dateJoined) {
            this.dateJoined = dateJoined;
        }

        public Optional<Date> getDateJoined() {
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
                    .add("dateJoined", dateJoined)
                    .add("role", role)
                    .add("salary", salary)
                    .toString();
        }
    }
}