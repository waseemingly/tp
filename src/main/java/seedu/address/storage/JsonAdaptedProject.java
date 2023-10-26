package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Name;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    private final String projectName;
    private final String description;
    private final List<String> deadlines = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given {@code projectName}.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName, @JsonProperty("description") String description,
                              @JsonProperty("deadlines") List<String> deadlines) {

        this.projectName = projectName;
        this.description = description;
        this.deadlines.clear();
        if (deadlines != null) {
            this.deadlines.addAll(deadlines);
        }
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {

        projectName = source.getProjectName().fullName;
        description = source.getProjectDescription().toString();
        deadlines.addAll(source.getProjectDeadlines().stream()
                .map(Deadline::getStringRepresentation).collect(Collectors.toList()));
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
        
        return new Project(projectModelName.fullName);
    }

}
