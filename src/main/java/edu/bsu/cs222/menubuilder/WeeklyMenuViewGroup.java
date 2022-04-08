package edu.bsu.cs222.menubuilder;

//Week auto-updater -- MAYBE

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class WeeklyMenuViewGroup extends Group {

    private final Menu currentMenu;

    public WeeklyMenuViewGroup(Menu menu) {
        currentMenu = menu;
        buildUI(menu);
    }

    private void buildUI(Menu menu) {
        HBox box = configureHBox();
        for (Day day: menu) {
            box.getChildren().add(new DayViewBox(day));
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
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
        editButton.setOnAction((e) -> this.getScene().setRoot(new WeeklyMenuEditorGroup(currentMenu)));
        return editButton;
    }

    private Button buildLoadButton() {
        Button loadButton = new Button("Load Menu");
        ButtonBar.setButtonData(loadButton, ButtonBar.ButtonData.RIGHT);
        loadButton.setOnAction((e) -> chooseMenuFile());
        return loadButton;
    }

    private void chooseMenuFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Menu File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json Files", "*.json")
        );
        File result = chooser.showOpenDialog(this.getScene().getWindow());
    }

    private static HBox configureHBox() {
        HBox box = new HBox();
        box.setMinSize(640, 480);
        return box;
    }
}
