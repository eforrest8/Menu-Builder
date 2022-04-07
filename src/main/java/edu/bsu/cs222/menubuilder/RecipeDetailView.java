package edu.bsu.cs222.menubuilder;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
            button.setOnAction(e -> openBrowserToURI(remoteRecipe.getURI()));
            return button;
        }
        return new Label("test label");
    }

    private void openBrowserToURI(URI uri) {
        try {
            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("linux")) {
                openBrowserOnLinux(uri);
            } else if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                throw new UnsupportedOperationException("Opening a web browser is not supported by your system.");
            }
        } catch (IOException | UnsupportedOperationException exception) {
            new Alert(Alert.AlertType.ERROR, "Encountered exception while trying to open web browser.\n" + exception.getLocalizedMessage()).show();
        }
    }

    private void openBrowserOnLinux(URI uri) throws IOException {
        // Workaround for Linux because "Desktop.getDesktop().browse()" doesn't work on some Linux implementations
        if (Runtime.getRuntime().exec(new String[] { "which", "xdg-open" }).getInputStream().read() != -1) {
            Runtime.getRuntime().exec(new String[] { "xdg-open", uri.toString() });
        } else {
            throw new UnsupportedOperationException("Can't launch default web browser; xdg-open not supported!");
        }
    }
}
