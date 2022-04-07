package edu.bsu.cs222.menubuilder;

//Week auto-updater -- MAYBE

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class WeeklyMenuViewScene extends Scene {

    public WeeklyMenuViewScene(Menu menu) {
        super(buildUI(menu));
    }

    private static Parent buildUI(Menu menu) {
        HBox box = configureHBox();
        for (Day day: menu) {
            box.getChildren().add(new DayViewBox(day));
        }
        return new Group(box);
    }

    private static HBox configureHBox() {
        HBox box = new HBox();
        box.setMinSize(640, 480);
        return box;
    }
}
