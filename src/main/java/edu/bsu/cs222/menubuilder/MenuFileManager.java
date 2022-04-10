package edu.bsu.cs222.menubuilder;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MenuFileManager {

    public void saveFile(Window window) {
        File result = showSaveFile(window);
        Menu menu = MenuSingleton.menu;
        try (FileWriter writer = new FileWriter(result)) {
            writer.write(new MenuSerializer(menu).serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File showSaveFile(Window window) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save File");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        return chooser.showSaveDialog(window);
    }

    public void loadFile() {
    }

    private File showOpenFile(Window window) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json Files", "*.json"));
        return chooser.showOpenDialog(window);
    }

}
