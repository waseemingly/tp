package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.model.developer.GithubId;
import seedu.address.model.developer.Rating;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.Address;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditDeveloperDescriptorBuilder {

    private EditDeveloperCommand.EditDeveloperDescriptor descriptor;

    public EditDeveloperDescriptorBuilder() {
        descriptor = new EditDeveloperCommand.EditDeveloperDescriptor();
    }

    public EditDeveloperDescriptorBuilder(EditDeveloperCommand.EditDeveloperDescriptor descriptor) {
        this.descriptor = new EditDeveloperCommand.EditDeveloperDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code developer}'s details
     */
    public EditDeveloperDescriptorBuilder(Developer developer) {
        descriptor = new EditDeveloperCommand.EditDeveloperDescriptor();
        descriptor.setName(developer.getName());
        descriptor.setPhone(developer.getPhone());
        descriptor.setEmail(developer.getEmail());
        descriptor.setAddress(developer.getAddress());
        descriptor.setDateJoined(developer.getDateJoined());
        descriptor.setRole(developer.getRole());
        descriptor.setSalary(developer.getSalary());
        descriptor.setGithubId(developer.getGithubId());
        descriptor.setRating(developer.getRating());
        descriptor.setProjects(developer.getProjects());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDeveloperDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDeveloperDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDeveloperDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditDeveloperDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    /*
    public EditDeveloperDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }
    */

    public EditDeveloperDescriptorBuilder withProjects(String... projects) {
        Set<String> projectNames = Stream.of(projects).collect(Collectors.toSet());
        descriptor.setProjects(projectNames);
        return this;
    }

    public EditDeveloperDescriptorBuilder withGithubId(String githubId) {
        descriptor.setGithubId(new GithubId(githubId));
        return this;
    }

    public EditDeveloperDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    public EditDeveloperDescriptorBuilder withDateJoined(String dateJoined) {
        descriptor.setDateJoined(new Date(dateJoined));
        return this;
    }

    public EditDeveloperDescriptorBuilder withRole(String role) {
        descriptor.setRole(new DeveloperRoles(role));
        return this;
    }

    public EditDeveloperDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    public EditDeveloperCommand.EditDeveloperDescriptor build() {
    /* public EditDeveloperDescriptorBuilder withTags(String... tags) {
        Set<String> projects = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setProjects(tagSet);
        return this;
    }*/
        return descriptor;
    }
}
