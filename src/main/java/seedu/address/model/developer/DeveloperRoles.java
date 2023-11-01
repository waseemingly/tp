package seedu.address.model.developer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.client.ClientRoles;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Developer's role in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class DeveloperRoles {
    private static List<DeveloperRoles> roles = new ArrayList<>();

    static {
        loadDeveloperRoles();
    }


    public static final String NO_SUCH_DEVELOPER_ROLE = "There is no such developer role, "
            + "please create role before proceeding";

    public final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public DeveloperRoles(String role) {
        requireNonNull(role);
        this.role = role;
    }

    public static void addDeveloperRole(DeveloperRoles role) {
        roles.add(role);
        saveDeveloperRoles();
    }

    public void deleteRole(DeveloperRoles role) {
        roles.remove(role);
        saveDeveloperRoles();
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String role) {
        return roles.toString().contains(role);
    }
    public static void saveDeveloperRoles() {
        try {
            // Save roles to a text file
            try (FileWriter writer = new FileWriter("DeveloperRoles.txt")) {
                for (DeveloperRoles role : roles) {
                    writer.write(role.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadDeveloperRoles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("DeveloperRoles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                roles.add(new DeveloperRoles(line));
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
        if (!(other instanceof DeveloperRoles)) {
            return false;
        }

        DeveloperRoles otherRole = (DeveloperRoles) other;
        return role.equals(otherRole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

}