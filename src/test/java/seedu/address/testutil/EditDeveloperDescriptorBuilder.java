package seedu.address.testutil;

import static seedu.address.logic.commands.TabIndex.Project;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.edit.EditDeveloperCommand;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.Address;
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
    public EditDeveloperDescriptorBuilder withTags(String... tags) {
        Set<String> tagSet = Stream.of(tags).collect(Collectors.toSet());
        descriptor.setProjects(tagSet);
        return this;
    }

    public EditDeveloperCommand.EditDeveloperDescriptor build() {
        return descriptor;
    }
}
