package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.Schedule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ScheduleEditorBox extends VBox {

    private final Schedule uneditedSchedule;
    private final Schedule editedSchedule;

    public ScheduleEditorBox(Schedule schedule) {
        uneditedSchedule = schedule;
        editedSchedule = schedule.copy();
        buildUI();
    }

    private void buildUI() {
        this.getChildren().clear();
        HBox box = configureHBox();
        for (Menu day: editedSchedule.getMenus()) {
            MenuEditorBox menuEditorBox = new MenuEditorBox(day);
            HBox.setHgrow(menuEditorBox, Priority.ALWAYS);
            box.getChildren().add(menuEditorBox);
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
    }

    private ButtonBar buildButtonBar() {
        ButtonBar bar = new ButtonBar();
        bar.getButtons().addAll(
                buildButton("Save & Close", ButtonBar.ButtonData.APPLY, event -> saveAndClose()),
                buildButton("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE,
                        (e) -> this.getScene().setRoot(new ScheduleViewBox(uneditedSchedule)))
        );
        return bar;
    }

    private void saveAndClose() {

        this.getScene().setRoot(new ScheduleViewBox(editedSchedule));
    }

    private Button buildButton(String text, ButtonBar.ButtonData buttonData, EventHandler<ActionEvent> onAction) {
        Button button = new Button(text);
        ButtonBar.setButtonData(button, buttonData);
        button.setOnAction(onAction);
        return button;
    }

    private static HBox configureHBox() {
        HBox box = new HBox();
        box.setMinSize(640, 480);
        return box;
    }

}
