package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;

public class RecipeAddMenu extends Dialog<WebRecipe> {

    private void buildUI() {
        HBox container = new HBox();
        Button addRecipe = new Button("Add Recipe From URL");
        addRecipe.setOnAction(e -> showAddRecipe());
        Button searchRecipe = new Button("Add recipe from search provider");
        container.getChildren().addAll(addRecipe, searchRecipe);
        this.getDialogPane().setContent(container);
    }

    private void showAddRecipe() {
        RecipeAddForm addForm = new RecipeAddForm();
        addForm.showAndWait();
        this.setResult(addForm.getResult());
        this.hide();
    }
}
