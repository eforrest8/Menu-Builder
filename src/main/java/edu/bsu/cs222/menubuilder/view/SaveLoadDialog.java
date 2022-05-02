package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.ScheduleDeserializer;
import edu.bsu.cs222.menubuilder.model.Schedule;
import edu.bsu.cs222.menubuilder.model.ScheduleSerializer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.Optional;

public class SaveLoadDialog {

    private final FileChooser chooser;

    public SaveLoadDialog() {
        chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
    }

    public void saveFile(Window window, Schedule schedule) {
        Alert yesNoAlert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save your menu before closing?", ButtonType.NO, ButtonType.YES);
        yesNoAlert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> new ScheduleSerializer(schedule).serialize(showSaveFile(window)));
    }

    private FileWriter showSaveFile(Window window) {
        chooser.setTitle("Save File");
        try {
            return new FileWriter(chooser.showSaveDialog(window));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Unable to save file.").showAndWait();
            return null;
        }
    }

    public Optional<Schedule> loadFile(Window window) {
        return Optional.ofNullable(new ScheduleDeserializer().deserialize(showOpenFile(window)));
    }

    private FileReader showOpenFile(Window window) {
        chooser.setTitle("Open File");
        try {
            return new FileReader(chooser.showOpenDialog(window));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Unable to load file.").showAndWait();
            return null;
        }
    }

}
