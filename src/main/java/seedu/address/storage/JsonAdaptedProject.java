package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Project;

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
        projectName = source.projectName;
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
        if (!Project.isValidProjectName(projectName)) {
            throw new IllegalValueException(Project.MESSAGE_CONSTRAINTS);
        }
        return new Project(projectName);
    }

}
