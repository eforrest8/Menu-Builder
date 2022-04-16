package edu.bsu.cs222.menubuilder.view;

//Week auto-updater -- MAYBE

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.MenuSingleton;
import edu.bsu.cs222.menubuilder.model.SaveLoadManager;
import edu.bsu.cs222.menubuilder.model.Schedule;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeeklyMenuViewGroup extends Group {

    private final Schedule schedule;

    public WeeklyMenuViewGroup(Schedule schedule) {
        this.schedule = schedule;
        buildUI();
    }

    private void buildUI() {
        HBox box = configureHBox();
        for (Menu day: MenuSingleton.schedule.getMenus()) {
            box.getChildren().add(new DayViewBox(day));
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
    }

    private void rebuildUI() {
        this.getChildren().clear();
        buildUI();
    }

    private ButtonBar buildButtonBar() {
        ButtonBar bar = new ButtonBar();
        bar.getButtons().addAll(
                buildLoadButton(),
                buildEditButton(),
                buildSettingsButton()
        );
        return bar;
    }

    private Button buildSettingsButton() {
        Button settingsButton = new Button("Settings");
        ButtonBar.setButtonData(settingsButton, ButtonBar.ButtonData.LEFT);
        return settingsButton;
    }

    private Button buildEditButton() {
        Button editButton = new Button("Edit Menu");
        ButtonBar.setButtonData(editButton, ButtonBar.ButtonData.RIGHT);
        editButton.setOnAction((e) -> this.getScene().setRoot(new WeeklyMenuEditorGroup(schedule)));
        return editButton;
    }

    private Button buildLoadButton() {
        Button loadButton = new Button("Load Menu");
        ButtonBar.setButtonData(loadButton, ButtonBar.ButtonData.RIGHT);
        loadButton.setOnAction((e) -> {
            new SaveLoadManager().loadFile(this.getScene().getWindow());
            rebuildUI();
        });
        return loadButton;
    }

    private static HBox configureHBox() {
        HBox box = new HBox();
        box.setMinSize(640, 480);
        return box;
    }
}
