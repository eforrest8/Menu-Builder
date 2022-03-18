package edu.bsu.cs222.menubuilder;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.IOException;
import java.net.URI;


public class RecipeDetailView extends VBox {
    public RecipeDetailView(Recipe recipe) {
        this.getChildren().addAll(
                getTitleLabel(recipe),
                getRecipeLocation(recipe)
        );
    }

    private Label getTitleLabel(Recipe recipe) {
        return new Label(recipe.getTitle());
    }

    private Node getRecipeLocation(Recipe recipe) {
        if (recipe instanceof RemoteRecipe remoteRecipe) {
            Button button = new Button("View recipe in browser");
            button.setOnAction(e -> linkHandler(remoteRecipe.getURI()));
            return button;
        }
        return new Label("test label");
    }

    private void linkHandler(URI uri) {
        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException ioException) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to launch browser");
        }
    }

    private void modifyDOM(Document document) {

    }

}
