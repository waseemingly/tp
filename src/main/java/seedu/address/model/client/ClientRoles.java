package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.developer.DeveloperRoles;

/**
 * Represents a Developer's role in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class ClientRoles {
    private static List<ClientRoles> roles = new ArrayList<>();
    public static final String NO_SUCH_CLIENT_ROLE = "There is no such client role, "
            + "please create role before proceeding";
    private static boolean noRepeat;
    private static boolean notDefault;
    private static boolean notInList;
    private static String listOfRoles;

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
        roles.add(new ClientRoles("Manager"));
        roles.add(new ClientRoles("Developer"));
        roles.add(new ClientRoles("HR"));
        roles.add(new ClientRoles("Client"));
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

    public static String printRoles() {
        listOfRoles = roles.toString();
        return listOfRoles;
    }

    public static boolean isRemovableRole(Model model, String role) {
        // are there information using it
        Predicate<Client> finalPredicate = client -> true;
        finalPredicate = finalPredicate.and(new RoleClientContainsKeywordsPredicate(Arrays.asList(role)));
        model.updateFilteredClientList(finalPredicate);
        int size = model.getFilteredClientList().size();
        if (size == 0) {
            noRepeat = true;
        } else {
            noRepeat = false;
        }

        // is it a default role
        if (role.contains("Manager") || role.contains("HR") || role.contains("Developer") || role.contains("Manager")) {
            notDefault = false;
        } else {
            notDefault = true;
        }

        // is it even in the list
        if (isValidRole(role)) {
            notInList = false;
        } else {
            notInList = true;
        }

        if (noRepeat && notDefault && notInList) {
            return false;
        } else {
            return true;
        }

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
            int lineCount = 1;

            while ((line = reader.readLine()) != null) {
                if (lineCount >= 5) {
                    roles.add(new ClientRoles(line));
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean isNotDefault() {
        return notDefault;
    }

    public static boolean isNoRepeat() {
        return noRepeat;
    }
    public static boolean isNotInList() {
        return notInList;
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
