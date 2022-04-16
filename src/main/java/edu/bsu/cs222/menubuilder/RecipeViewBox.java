package edu.bsu.cs222.menubuilder;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class RecipeViewBox extends VBox {

    public RecipeViewBox(WebRecipe recipe) {
        this.getChildren().addAll(
                getTitleLabel(recipe),
                getRecipeLocation(recipe)
        );
    }

    private Label getTitleLabel(WebRecipe recipe) {
        Label title = new Label(recipe.getTitle());
        title.setWrapText(true);
        title.setTextOverrun(OverrunStyle.WORD_ELLIPSIS);
        title.setMaxSize(128, 96);
        return title;
    }

    private Node getRecipeLocation(WebRecipe recipe) {
        Button button = new Button("View Recipe");
        button.setOnAction(e -> {
            try {
                BrowserLauncher.openBrowserToURI(recipe.getURI());
            } catch (IOException exception) {
                new Alert(Alert.AlertType.ERROR,
                        "Encountered exception while trying to open web browser.\n" +
                                exception.getLocalizedMessage()).show();
            }
        });
        return button;
    }

}
