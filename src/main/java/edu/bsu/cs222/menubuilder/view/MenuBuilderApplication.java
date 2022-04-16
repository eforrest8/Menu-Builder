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

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setScene(buildUI(initializeSchedule()));
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
        fileManager.saveFile(stage.getOwner());
        Platform.exit();
    }

    private Scene buildUI(Schedule schedule) {
        return new Scene(new WeeklyMenuViewGroup(schedule));
    }

}
