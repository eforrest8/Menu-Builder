package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.Schedule;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

// Builds viewable and clickable
// Edit, Load, and Settings buttons

public class ScheduleViewBox extends VBox {

    private Runnable onEdit;
    private Runnable onLoadRequest;

    public void setOnEdit(Runnable runnable) {
        this.onEdit = runnable;
    }

    public void setOnLoadRequest(Runnable callback) {
        this.onLoadRequest = callback;
    }

    public ScheduleViewBox(Schedule schedule) {
        buildUI(schedule);
    }

    private void buildUI(Schedule schedule) {
        this.getChildren().clear();
        this.setPrefSize(720, 480);
        this.setPadding(new Insets(4));
        HBox box = new HBox();
        box.setSpacing(2);
        VBox.setVgrow(box, Priority.ALWAYS);
        for (Menu menu: schedule.getMenus()) {
            MenuViewBox menuViewBox = new MenuViewBox(menu);
            HBox.setHgrow(menuViewBox, Priority.ALWAYS);
            box.getChildren().add(menuViewBox);
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
    }

    private ButtonBar buildButtonBar() {
        ButtonBar bar = new ButtonBar();
        bar.setPadding(new Insets(0, 0, 4, 0));
        bar.getButtons().addAll(
                buildLoadButton(),
                buildEditButton(),
                buildSettingsButton()
        );
        return bar;
    }
    // remove? - A
    private Button buildSettingsButton() {
        Button settingsButton = new Button("Settings");
        ButtonBar.setButtonData(settingsButton, ButtonBar.ButtonData.LEFT);
        return settingsButton;
    }

    private Button buildEditButton() {
        Button editButton = new Button("Edit Menu");
        ButtonBar.setButtonData(editButton, ButtonBar.ButtonData.RIGHT);
        editButton.setOnAction((e) -> onEdit.run());
        return editButton;
    }

    private Button buildLoadButton() {
        Button loadButton = new Button("Load Menu");
        ButtonBar.setButtonData(loadButton, ButtonBar.ButtonData.RIGHT);
        loadButton.setOnAction(e -> onLoadRequest.run());
        return loadButton;
    }

}
