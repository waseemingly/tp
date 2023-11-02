package seedu.address.model.developer;
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

/**
 * Represents a Developer's role in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class DeveloperRoles {
    private static List<DeveloperRoles> roles = new ArrayList<>();
    private static boolean noRepeat;
    private static boolean notDefault;
    private static boolean notInList;
    private static String listOfRoles;

    static {
        roles.add(new DeveloperRoles("Frontend Developer"));
        roles.add(new DeveloperRoles("Backend Developer"));
        roles.add(new DeveloperRoles("Developer"));
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

    public static boolean isRemovableRole(Model model, String role) {

        Predicate<Developer> finalPredicate = developer -> true;
        finalPredicate = finalPredicate.and(new RoleDeveloperContainsKeywordsPredicate(Arrays.asList(role)));
        model.updateFilteredDeveloperList(finalPredicate);
        int size = model.getFilteredDeveloperList().size();
        if (size == 0) {
            noRepeat = true;
        } else {
            noRepeat = false;
        }

        if (role.contains("Frontend Developer") || role.contains("Backend Developer") || role.contains("Developer")) {
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

    public static void deleteDeveloperRole(DeveloperRoles role) {
        roles.remove(role);
        saveDeveloperRoles();
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
            int lineCount = 1;

            while ((line = reader.readLine()) != null) {
                if (lineCount >= 4) {
                    roles.add(new DeveloperRoles(line));
                }
                lineCount++;
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
