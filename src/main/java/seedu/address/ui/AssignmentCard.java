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
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.Group;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class AssignmentCard extends UiPart<Region> {

    private static final String FXML = "AssignmentListCard.fxml";

    public final Assignment assignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label label;
    @FXML
    private FlowPane group;
    @FXML
    private Label dueDate;
    @FXML
    private Label id;

    /**
     * Creates a {@code AssignmentCard} with the given {@code Assignment} and index to display.
     */
    public AssignmentCard(Assignment assignment, int displayedIndex) {
        super(FXML);
        this.assignment = assignment;
        id.setText(assignment.getAssignmentId().toString() + ". ");
        label.setText(assignment.getLabel().label);
        dueDate.setText("Due by: " + assignment.getDueDate().toString());
        group.prefWrapLengthProperty().bind(Bindings.max(0, cardPane.widthProperty().subtract(30)));
        Set<Group> groups = assignment.getGroups();
        groups.stream()
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
