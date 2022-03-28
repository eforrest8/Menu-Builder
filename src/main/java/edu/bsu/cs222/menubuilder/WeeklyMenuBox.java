package edu.bsu.cs222.menubuilder;

import javafx.scene.layout.HBox;

public class WeeklyMenuBox extends HBox {
    private Menu menu;
    public WeeklyMenuBox(Menu menu) {
        this.menu = menu;
        this.setMinSize(640, 480);
    }

    private void buildUI() {
        for (Day day:
                menu) {
            this.getChildren().add(new DayView(day));
        }
    }
}
