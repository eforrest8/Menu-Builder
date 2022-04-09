package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RecipeAddForm extends Dialog<WebRecipe> {
    private final TextField titleInput = new TextField();
    private final TextField urlInput = new TextField();

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

    private WebRecipe convertResult(ButtonType buttonType) {
        if (buttonType.getButtonData().isCancelButton()) {
            return null;
        }
        return new WebRecipe(titleInput.getText(), urlInput.getText());
    }
}
