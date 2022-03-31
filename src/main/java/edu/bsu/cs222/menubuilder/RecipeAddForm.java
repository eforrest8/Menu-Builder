package edu.bsu.cs222.menubuilder;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecipeAddForm extends Dialog<Recipe> {
    private TextField titleInput = new TextField();
    private TextField urlInput = new TextField();

    public RecipeAddForm() {
        buildUI();
    }

    private void buildUI() {
        Button confirm = new Button("Confirm");
        confirm.setDefaultButton(true);
        confirm.setOnAction(e -> {
            setResult(new WebRecipe(titleInput.getText(), urlInput.getText()));
            this.close();
        });
        VBox container = new VBox();
        container.getChildren().addAll(titleInput, urlInput, confirm);
        this.getDialogPane().setContent(container);
        setResultConverter(this::convertResult);
    }

    private Recipe convertResult(ButtonType buttonType) {
        if (buttonType.getButtonData().isCancelButton()) {
            return null;
        }
        return new WebRecipe(titleInput.getText(), urlInput.getText());
    }
}
