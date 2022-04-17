package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class MenuEditorBox extends VBox {

    private final Menu menu;

    public MenuEditorBox(Menu menu) {
        this.menu = menu;
        buildUI();
    }

    private void buildUI() {
        this.getChildren().clear();
        this.getChildren().add(new Label(menu.getName()));
        for (WebRecipe recipe: menu.getRecipes()) {
            this.getChildren().add(
                    new VBox(
                            new HBox(buildShiftButtons(recipe), new RecipeViewBox(recipe)),
                            buildRemoveButton(recipe)));
        }
        appendAddRecipeButton();
    }

    private Button buildRemoveButton(WebRecipe recipe) {
        Button removeButton = new Button("Remove Recipe");
        removeButton.setOnAction( e -> {
            menu.removeRecipe(recipe);
            buildUI();
        });
        return removeButton;
    }

    private VBox buildShiftButtons(WebRecipe recipe) {
        Button shiftUp = new Button("\u2191");
        Button shiftDown = new Button("\u2193");
        shiftUp.setOnAction( e -> {
            menu.shiftRecipeUp(recipe);
            buildUI();
        });
        shiftDown.setOnAction( e -> {
            menu.shiftRecipeDown(recipe);
            buildUI();
        });
        return new VBox(shiftUp, shiftDown);
    }

    private void appendAddRecipeButton() {
        Button addRecipeButton = new Button("Add Recipe");
        addRecipeButton.setOnAction((e) -> {
            RecipeSearchDialog searchDialog = new RecipeSearchDialog();
            Optional<WebRecipe> result = searchDialog.showAndWait();
            result.ifPresent(menu::addRecipe);
            this.buildUI();
        });
        this.getChildren().add(addRecipeButton);
    }

}

