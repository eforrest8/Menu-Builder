package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayEditorBox extends VBox{
    private final Day day;

    public DayEditorBox(Day day) {
        this.day = day;
        buildUI();
    }

    private void buildUI() {
        this.setMinSize(96, 512);
        this.getChildren().addAll(
                new Label(day.getName()),
                new Label(Integer.toString(day.getIndex()))
        );
        for (Recipe recipe: day) {
            this.getChildren().add(new RecipeDetailView(recipe));
        }
        Button button1 = new Button("Open Editor");
        button1.setOnAction((e) -> new DayEditorBox(day));
    }
    private void setButtonFunctions(){

    }
}

