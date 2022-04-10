package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayViewBox extends VBox {

    public DayViewBox(WeekDay day) {
        buildUI(day);
    }

    private void buildUI(WeekDay day) {
        configure(day);
        addChildren(day);
    }

    private void addChildren(WeekDay day) {
        this.getChildren().add(new Label(day.getName()));
        for (WebRecipe recipe: day) {
            this.getChildren().add(new RecipeViewBox(recipe));
        }
    }

    private void configure(WeekDay day) {
        this.setMinSize(96, 512);
    }
}
