package seedu.address.model.Client;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.*;
import seedu.address.model.project.Project;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Client in the address book, extending the Person class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client extends Person {
    private final Name organisation;
    private final Document document;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Role role, Set<String> projects,
                  Name organisation, Document document) {
        super(name, phone, email, address, role, projects);
        requireAllNonNull(organisation, document);
        this.organisation = organisation;
        this.document = document;
    }

    public Name getOrganisation() {
        return organisation;
    }

    public Document getDocument() {
        return document;
    }

    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return super.equals(otherClient) && organisation.equals(otherClient.organisation)
                && document.equals(otherClient.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), organisation, document);
    }

    @Override
    public String toString() {
        return super.toString() + " Organisation: " + organisation + " Document: " + document;
    }
}