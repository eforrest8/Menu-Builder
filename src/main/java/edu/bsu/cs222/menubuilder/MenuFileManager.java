package edu.bsu.cs222.menubuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class MenuFileManager {

    void writeFile(File file) {
        if (file == null) {
            return;
        }
        Menu menu = MenuSingleton.menu;
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(new MenuSerializer(menu).serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(File file) {
        if (file == null) {
            return;
        }
        SavedMenuParser parser = new SavedMenuParser();
        try {
            String jsonString = Files.readString(file.toPath());
            MenuSingleton.menu = parser.parse(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}