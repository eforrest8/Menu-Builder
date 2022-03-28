package edu.bsu.cs222.menubuilder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayView extends VBox {
    private final Day day;

    public DayView(Day day) {
        this.day = day;
        buildUI();
    }

    private void buildUI() {
        this.setMinSize(96, 512);
        this.getChildren().addAll(
                new Label(day.getName()),
                new Label(Integer.toString(day.getIndex()))
        );
        for (Recipe recipe :
                day) {
            this.getChildren().add(new RecipeDetailView(recipe));
        }
    }
}
