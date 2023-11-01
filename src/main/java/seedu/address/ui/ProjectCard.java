package seedu.address.ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;

import java.util.stream.Collectors;


/**
 * A UI component that displays information of a {@code Project}.
 */
public class ProjectCard extends UiPart<Region> {

    private static final String FXML = "ProjectListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Project project;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;

    @FXML
    private ProgressBar progress;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private TableView<Data> table;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ProjectCard (Project project, int displayedIndex) {
        super(FXML);
        this.project = project;
        id.setText(displayedIndex + ". ");
        name.setText(project.getName());
        description.setText(project.getProjectDescription().desc);
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(95);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setMinWidth(260);
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        TableColumn priorityCol = new TableColumn("Priority");
        priorityCol.setMinWidth(95);
        priorityCol.setCellValueFactory(
                new PropertyValueFactory<>("priority"));
        TableColumn doneCol = new TableColumn("Done");
        doneCol.setMinWidth(95);
        doneCol.setCellValueFactory(
                new PropertyValueFactory<>("isDone"));
        TableColumn indexCol = new TableColumn("#");
        indexCol.setMinWidth(20);
        indexCol.setCellValueFactory(
                new PropertyValueFactory<>("index"));
        table.getColumns().addAll(indexCol, doneCol, priorityCol,dateCol,descriptionCol);
        ObservableList<Data> data = FXCollections.observableList(project.getProjectDeadlines().stream()
                .map(deadline ->new Data(deadline)).collect(Collectors.toList()));
        progress.setProgress(data.stream().filter(t->t.getIsDone()).count()*1.0/data.size());
        table.setItems(data);
    }
    public static class Data {
        private final SimpleStringProperty date;
        private final SimpleStringProperty description;
        private final SimpleStringProperty priority;

        private final SimpleBooleanProperty isDone;
        private final SimpleIntegerProperty index;
        
        private Data(Deadline deadline){
            date = new SimpleStringProperty(deadline.getDate().toString());
            description = new SimpleStringProperty(deadline.getDescription().desc);
            priority = new SimpleStringProperty(deadline.getPriority().toString());
            isDone = new SimpleBooleanProperty(deadline.getIsDone());
            index = new SimpleIntegerProperty(deadline.getNum());
        }
        
        public int getIndex() { return index.get(); }
        public void setIndex(int num) { this.index.set(num);}
        public String getDate() {
            return date.get();
        }

        public void setDate(String date) {
            this.date.set(date);
        }
     
        public boolean getIsDone() {
            return isDone.get();
        }

        public void setIsDone(boolean isDone) {
            this.isDone.set(isDone);
        }
        
        public String getDescription() {
            return description.get();
        }
        
        public void setDescription(String description) {
            this.description.set(description);
        }
        
        public String getPriority() {
            return priority.get();
        }
        
        public void setPriority(String priority) {
            this.priority.set(priority);
        }

    }
}