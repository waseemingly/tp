package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.project.Project;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    private final String projectName;

    /**
     * Constructs a {@code JsonAdaptedProject} with the given {@code projectName}.
     */
    @JsonCreator
    public JsonAdaptedProject(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().fullName;
    }

    @JsonValue
    public String getTagName() {
        return projectName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Project toModelType() throws IllegalValueException {
        if (!Name.isValidName(projectName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name projectModelName = new Name(projectName);
        
        return new Project(projectModelName);
    }

}
