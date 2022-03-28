package edu.bsu.cs222.menubuilder;

import javafx.scene.layout.HBox;

public class WeeklyMenuBox extends HBox {
    private Menu menu;
    public WeeklyMenuBox(Menu menu) {
        this.menu = menu;
        this.buildUI();
    }

    private void buildUI() {
        this.setMinSize(640, 480);
        for (Day day:
                menu) {
            this.getChildren().add(new DayView(day));
        }
    }
}
