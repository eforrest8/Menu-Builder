package edu.bsu.cs222.menubuilder;

//Week auto-updater -- MAYBE

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeeklyMenuViewGroup extends Group {

    public WeeklyMenuViewGroup() {
        buildUI();
    }

    private void buildUI() {
        HBox box = configureHBox();
        for (WeekDay day: MenuSingleton.menu.days()) {
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
        editButton.setOnAction((e) -> this.getScene().setRoot(new WeeklyMenuEditorGroup()));
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
