package edu.bsu.cs222.menubuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeeklyMenuEditorGroup extends Group {

    private final Menu editedMenu;

    public WeeklyMenuEditorGroup() {
        editedMenu = MenuSingleton.menu;
        buildUI();
    }

    private void buildUI() {
        HBox box = configureHBox();
        for (WeekDay day: editedMenu) {
            box.getChildren().add(new DayEditorBox(day));
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
    }

    private ButtonBar buildButtonBar() {
        ButtonBar bar = new ButtonBar();
        bar.getButtons().addAll(
                buildButton("Save & Close", ButtonBar.ButtonData.APPLY, event -> saveAndClose()),
                buildButton("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE,
                        (e) -> this.getScene().setRoot(new WeeklyMenuViewGroup()))
        );
        return bar;
    }

    private void saveAndClose() {
        MenuSingleton.menu = editedMenu;
        this.getScene().setRoot(new WeeklyMenuViewGroup());
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
