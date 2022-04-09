package edu.bsu.cs222.menubuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WeeklyMenuEditorGroup extends Group {

    private final Menu initialMenu;
    private final Menu editedMenu;

    public WeeklyMenuEditorGroup(Menu menu) {
        initialMenu = menu;
        editedMenu = menu;
        buildUI(menu);
    }

    private void buildUI(Menu menu) {
        HBox box = configureHBox();
        for (Day day: menu) {
            box.getChildren().add(new DayEditorBox(day));
        }
        this.getChildren().add(new VBox(buildButtonBar(), box));
    }

    private ButtonBar buildButtonBar() {
        EdamamAPI eda = new EdamamAPI();
        ButtonBar bar = new ButtonBar();
        bar.getButtons().addAll(
                buildButton("Save & Close", ButtonBar.ButtonData.APPLY, this::saveAndClose),
                buildButton("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE,
                        (e) -> this.getScene().setRoot(new WeeklyMenuViewGroup(initialMenu))),
                buildButton("Google Search", ButtonBar.ButtonData.APPLY, this::openBrowse)
        );
        return bar;
    }

    private void saveAndClose(ActionEvent event) {
        System.out.println("1");
        this.getScene().setRoot(new WeeklyMenuViewGroup(editedMenu));
    }

    private void openBrowse(ActionEvent event) {
        System.out.println("1");
        URL url = null;
        try {
            url = new URL("https://www.google.com/search?q=lasagna");
        } catch (Exception e) {
            System.out.println("Wrong URL Format");
        }
        EdamamAPI eda = new EdamamAPI();
        try {
            eda.gSearch(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Button buildButton(String text, ButtonBar.ButtonData buttonData, EventHandler<ActionEvent> onAction) {
        Button button = new Button(text);
        ButtonBar.setButtonData(button, buttonData);
        button.setOnAction(onAction);
        return button;
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
