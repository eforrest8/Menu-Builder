package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Schedule;
import edu.bsu.cs222.menubuilder.model.WeeklyScheduleBuilder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuBuilderApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Schedule schedule = initializeSchedule();
        primaryStage.setScene(new Scene(new Group()));
        new MenuStateChangeIntermediary(primaryStage, schedule);
    }

    private Schedule initializeSchedule() {
        return new WeeklyScheduleBuilder().buildWeeklySchedule();
    }

}
