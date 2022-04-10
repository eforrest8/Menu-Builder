package edu.bsu.cs222.menubuilder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuBuilderApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(buildUI());
        primaryStage.show();
    }

    private Scene buildUI() {
        Menu menu = new WeeklyMenu();
        return new Scene(new WeeklyMenuViewGroup(menu));
    }

}
