package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;

public class SaveLoadManager {

    private final MenuFileManager menuFileManager = new MenuFileManager();

    public void saveFile(Window window) {
        Alert yesNoAlert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save your menu before closing?", ButtonType.NO, ButtonType.YES);
        yesNoAlert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> menuFileManager.writeFile(showSaveFile(window)));
    }

    private File showSaveFile(Window window) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save File");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        return chooser.showSaveDialog(window);
    }

    public void loadFile(Window window) {
        menuFileManager.loadFile(showOpenFile(window));
    }

    private File showOpenFile(Window window) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        return chooser.showOpenDialog(window);
    }

}
