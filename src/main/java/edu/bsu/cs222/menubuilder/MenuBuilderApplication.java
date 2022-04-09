package edu.bsu.cs222.menubuilder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class MenuBuilderApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(buildUI());
        primaryStage.show();
    }

    private Scene buildUI() {
        Menu menu = new WeeklyMenu();
        Button testRecipeSearch = new Button("open search menu");
        testRecipeSearch.setOnAction(e -> showRecipeSearchStage());
        return new Scene(new WeeklyMenuViewGroup(menu));
    }

    private void showRecipeSearchStage() {
        Dialog<WebRecipe> recipeSearchStage = new RecipeAddMenu();
        recipeSearchStage.show();
        WebRecipe result = recipeSearchStage.getResult();
    }

}
