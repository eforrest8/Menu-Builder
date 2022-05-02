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

import java.util.function.Consumer;

public class ScheduleEditorBox extends VBox {

    private final Schedule editedSchedule;
    private Consumer<Schedule> onSaveAndClose;
    private Runnable onCancel;

    public void setOnSaveAndClose(Consumer<Schedule> consumer) {
        this.onSaveAndClose = consumer;
    }

    public void setOnCancel(Runnable runnable) {
        this.onCancel = runnable;
    }

    public ScheduleEditorBox(Schedule schedule) {
        editedSchedule = schedule.copy();
        buildUI();
    }

    private void buildUI() {
        this.getChildren().clear();
        HBox box = configureHBox();
        for (Menu day : editedSchedule.menus()) {
            MenuEditorBox menuEditorBox = new MenuEditorBox(day);
            HBox.setHgrow(menuEditorBox, Priority.ALWAYS);
            box.getChildren().add(menuEditorBox);
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
    }

    private static HBox configureHBox() {
        HBox box = new HBox();
        box.setMinSize(640, 480);
        return box;
    }

    private ButtonBar buildButtonBar() {
        ButtonBar bar = new ButtonBar();
        bar.getButtons().addAll(
                buildButton("Save & Close", ButtonBar.ButtonData.APPLY, event -> onSaveAndClose.accept(editedSchedule)),
                buildButton("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE,
                        event -> onCancel.run())
        );
        return bar;
    }

    private Button buildButton(String text, ButtonBar.ButtonData buttonData, EventHandler<ActionEvent> onAction) {
        Button button = new Button(text);
        ButtonBar.setButtonData(button, buttonData);
        button.setOnAction(onAction);
        return button;
    }

}
