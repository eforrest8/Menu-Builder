package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.SaveLoadManager;
import edu.bsu.cs222.menubuilder.model.Schedule;
import edu.bsu.cs222.menubuilder.model.WeeklyScheduleBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MenuBuilderApplication extends Application {

    private Stage stage;
    private Schedule schedule;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        schedule = initializeSchedule();
        stage.setScene(buildUI(schedule));
        stage.setOnCloseRequest(this::handleCloseRequest);
        stage.show();
    }

    private Schedule initializeSchedule() {
        return new WeeklyScheduleBuilder().buildWeeklySchedule();
    }

    private void handleCloseRequest(WindowEvent windowEvent) {
        if (windowEvent.getEventType() != WindowEvent.WINDOW_CLOSE_REQUEST) {
            return;
        }
        SaveLoadManager fileManager = new SaveLoadManager();
        fileManager.saveFile(stage.getOwner(), schedule);
        Platform.exit();
    }

    private Scene buildUI(Schedule schedule) {
        return new Scene(new ScheduleViewBox(schedule));
    }

}
