package seedu.address.model.client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import seedu.address.model.developer.DeveloperRoles;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Developer's role in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class ClientRoles {
    private static List<ClientRoles> roles = new ArrayList<>();
    public static final String NO_SUCH_CLIENT_ROLE = "There is no such client role, "
            + "please create role before proceeding";

    public final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public ClientRoles(String role) {
        requireNonNull(role);
        this.role = role;
    }
    static {
        loadClientRoles();
    }

    public static void addClientRole(ClientRoles role) {
        roles.add(role);
        saveClientRoles();
    }

    public static void deleteClientRole(ClientRoles role) {
        roles.remove(role);
        saveClientRoles();
    }
    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String role) {
        return roles.toString().contains(role);
    }

    public static void saveClientRoles() {
        try {
            // Save roles to a text file
            try (FileWriter writer = new FileWriter("ClientRoles.txt")) {
                for (ClientRoles role : roles) {
                    writer.write(role.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadClientRoles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ClientRoles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                roles.add(new ClientRoles(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientRoles)) {
            return false;
        }

        ClientRoles otherRole = (ClientRoles) other;
        return role.equals(otherRole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

}