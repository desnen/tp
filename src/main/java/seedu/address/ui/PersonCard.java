package seedu.address.ui;

import java.util.Set;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane group;
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(person.getStudentId() + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        email.setText("Email: " + person.getEmail().value);
        group.prefWrapLengthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(30)));
        Set<Group> groupSet = person.getGroups();
        groupSet.stream()
                .forEach(g -> {
                    Label groupLabel = new Label(g.getGroupName().toString());
                    groupLabel.setWrapText(true);
                    groupLabel.maxWidthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(30)));
                    groupLabel.setMinWidth(0);
                    groupLabel.getStyleClass().add("group-bubble");
                    cardPane.widthProperty().addListener((observable, oldValue, newValue) ->
                            updateGroupLabelWidth(groupLabel));
                    group.getChildren().add(groupLabel);
                    Platform.runLater(() -> updateGroupLabelWidth(groupLabel));
                });
    }

    private void updateGroupLabelWidth(Label groupLabel) {
        double availableWidth = Math.max(0, cardPane.getWidth() - 30);
        if (availableWidth == 0) {
            return;
        }

        Text text = new Text(groupLabel.getText());
        text.setFont(groupLabel.getFont());
        double naturalWidth = text.getLayoutBounds().getWidth() + 16;

        if (naturalWidth > availableWidth) {
            groupLabel.setPrefWidth(availableWidth);
        } else {
            groupLabel.setPrefWidth(Region.USE_COMPUTED_SIZE);
        }
    }
}
