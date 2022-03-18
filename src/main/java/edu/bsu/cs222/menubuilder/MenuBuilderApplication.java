package edu.bsu.cs222.menubuilder;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuBuilderApplication extends Application {
    private static Recipe recipe = new WebRecipe("https://www.allrecipes.com/recipe/16310/corned-beef-and-cabbage-i/");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(buildUI());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Parent buildUI() {
        return new RecipeDetailView(recipe);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
