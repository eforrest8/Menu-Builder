package edu.bsu.cs222.menubuilder;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;


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
            button.setOnAction(e -> handleLink(remoteRecipe.getURI()));
            return button;
        }
        return new Label("test label");
    }

    private void handleLink(URI uri) {
        try {
            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("linux")) {
                // Workaround for Linux because "Desktop.getDesktop().browse()" doesn't work on some Linux implementations
                if (Runtime.getRuntime().exec(new String[] { "which", "xdg-open" }).getInputStream().read() != -1) {
                    Runtime.getRuntime().exec(new String[] { "xdg-open", uri.toString() });
                } else {
                    new Alert(Alert.AlertType.ERROR, "xdg-open not supported!").show();
                }
            } else if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                throw new UnsupportedOperationException("Opening a browser is not supported.");
            }
        } catch (IOException | UnsupportedOperationException exception) {
            new Alert(Alert.AlertType.ERROR, "Unable to launch browser").show();
        }
    }
}
