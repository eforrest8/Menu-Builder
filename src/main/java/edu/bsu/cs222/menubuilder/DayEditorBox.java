package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.Optional;

public class DayEditorBox extends VBox {

    private final WeekDay day;

    public DayEditorBox(WeekDay day) {
        this.day = day;
        buildUI();
    }

    private void buildUI() {
        this.getChildren().clear();
        this.setMinSize(96, 512);
        this.getChildren().add(new Label(day.getName()));
        for (WebRecipe recipe: day) {
            this.getChildren().add(
                    new VBox(
                            new HBox(buildShiftButtons(day.indexOf(recipe)), new RecipeViewBox(recipe)),
                            buildRemoveButton(day.indexOf(recipe))));
        }
        appendAddRecipeButton();
    }

    private Button buildRemoveButton(int index) {
        Button removeButton = new Button("Remove Recipe");
        removeButton.setOnAction( e -> {
            day.remove(index);
            buildUI();
        });
        return removeButton;
    }

    private VBox buildShiftButtons(int index) {
        Button shiftUp = new Button("⬆");
        Button shiftDown = new Button("⬇");
        shiftUp.setOnAction( e -> {
            try {
                Collections.swap(day, index, index - 1);
                buildUI();
            } catch (IndexOutOfBoundsException ignored) {}
        });
        shiftDown.setOnAction( e -> {
            try {
                Collections.swap(day, index, index + 1);
                buildUI();
            } catch (IndexOutOfBoundsException ignored) {}
        });
        return new VBox(shiftUp, shiftDown);
    }

    private void appendAddRecipeButton() {
        Button addRecipeButton = new Button("Add Recipe");
        addRecipeButton.setOnAction((e) -> {
            RecipeSearchDialog searchDialog = new RecipeSearchDialog();
            Optional<WebRecipe> result = searchDialog.showAndWait();
            result.ifPresent(day::add);
            this.buildUI();
        });
        this.getChildren().add(addRecipeButton);
    }

}

