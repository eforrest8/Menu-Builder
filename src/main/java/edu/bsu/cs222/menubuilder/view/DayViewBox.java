package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayViewBox extends VBox {

    public DayViewBox(Menu day) {
        buildUI(day);
    }

    private void buildUI(Menu day) {
        configure();
        addChildren(day);
    }

    private void addChildren(Menu day) {
        this.getChildren().add(new Label(day.getName()));
        for (WebRecipe recipe: day.getRecipes()) {
            this.getChildren().add(new RecipeViewBox(recipe));
        }
    }

    private void configure() {
        this.setMinSize(96, 512);
    }
}
