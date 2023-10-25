package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Client.Client;
import seedu.address.model.person.Developer;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Developer> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Developer> developerList) {
        super(FXML);
        personListView.setItems(developerList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Developer} using a {@code DeveloperCard}.
     */
    class PersonListViewCell extends ListCell<Developer> {
        @Override
        protected void updateItem(Developer developer, boolean empty) {
            super.updateItem(developer, empty);

            if (empty || developer == null) {
                setGraphic(null);
                setText(null);
            } else if (developer instanceof seedu.address.model.developer.Developer) {
                setGraphic(new DeveloperCard((seedu.address.model.developer.Developer) developer, getIndex() + 1).getRoot());
            } else {
                setGraphic(new ClientCard((Client) developer, getIndex() + 1).getRoot());
            }
        }
    }

}
