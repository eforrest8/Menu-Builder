package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayViewBox extends VBox {

    public DayViewBox(Day day) {
        buildUI(day);
    }

    private void buildUI(Day day) {
        configure(day);
        addChildren(day);
    }

    private void addChildren(Day day) {
        this.getChildren().addAll(
                new Label(day.getName()),
                new Label(Integer.toString(day.getIndex()))
        );
        for (Recipe recipe: day) {
            this.getChildren().add(new RecipeDetailView(recipe));
        }
    }

    private void configure(Day day) {
        this.setMinSize(96, 512);
    }
}
