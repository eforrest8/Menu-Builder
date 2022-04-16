package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.SaveLoadManager;
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
        stage.setScene(buildUI());
        stage.setOnCloseRequest(this::handleCloseRequest);
        stage.show();
    }

    private void handleCloseRequest(WindowEvent windowEvent) {
        if (windowEvent.getEventType() != WindowEvent.WINDOW_CLOSE_REQUEST) {
            return;
        }
        SaveLoadManager fileManager = new SaveLoadManager();
        fileManager.saveFile(stage.getOwner());
        Platform.exit();
    }

    private Scene buildUI() {
        return new Scene(new WeeklyMenuViewGroup());
    }

}
