package edu.bsu.cs222.menubuilder.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class MenuFileManager {

    void writeFile(File file, Schedule schedule) {
        if (file == null) {
            return;
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(new MenuSerializer(schedule).serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Schedule> loadFile(File file) {
        if (file == null) {
            return Optional.empty();
        }
        SavedMenuParser parser = new SavedMenuParser();
        try {
            String jsonString = Files.readString(file.toPath());
            return Optional.of(parser.parse(jsonString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}