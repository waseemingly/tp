package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.name.Name;
import seedu.address.model.person.*;
import seedu.address.model.project.Project;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String dateJoined;
    private final String username;
    private final String password;
    private final String role;
    private final String salary;
    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("dateJoined") String dateJoined, @JsonProperty("username") String username,
                             @JsonProperty("password") String password, @JsonProperty("role") String role,
                             @JsonProperty("salary") String salary,
                             @JsonProperty("projects") List<JsonAdaptedProject> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateJoined = dateJoined;
        this.username = username;
        this.password = password;
        this.role = role;
        this.salary = salary;
        if (tags != null) {
            this.projects.addAll(tags);
        }
    }

    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        dateJoined = source.getDateJoined().toString();
        username = source.getUsername().username;
        password = source.getPassword().password;
        role = source.getRole().role;
        salary = source.getSalary().toString();
        projects.addAll(source.getProjects().stream()
                .map(JsonAdaptedProject::new)
                .collect(Collectors.toList()));
    }

    public Person toModelType() throws IllegalValueException {
        final List<Project> personProjects = new ArrayList<>();
        for (JsonAdaptedProject tag : projects) {
            personProjects.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (dateJoined == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DateJoined.class.getSimpleName()));
        }
        if (!DateJoined.isValidDate(dateJoined)) {
            throw new IllegalValueException(DateJoined.MESSAGE_CONSTRAINTS);
        }
        final DateJoined modelDateJoined = new DateJoined(dateJoined);

        if (username == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Username.class.getSimpleName()));
        }
        if (!Username.isValidUsername(username)) {
            throw new IllegalValueException(Username.MESSAGE_CONSTRAINTS);
        }
        final Username modelUsername = new Username(username);

        if (password == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Password.class.getSimpleName()));
        }
        if (!Password.isValidPassword(password)) {
            throw new IllegalValueException(Password.MESSAGE_CONSTRAINTS);
        }
        final Password modelPassword = new Password(password);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);
        final Set<Project> modelProjects = new HashSet<>(personProjects);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelDateJoined,
                modelUsername, modelPassword, modelRole, modelSalary, modelProjects);
    }

}
