package edu.bsu.cs222.menubuilder;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.DayOfWeek;

public class MenuBuilderApplication extends Application {
    private static Recipe recipe = new WebRecipe("corned beef and cabbage", "https://www.allrecipes.com/recipe/16310/corned-beef-and-cabbage-i/");

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(buildUI());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Parent buildUI() {
        Menu menu = new Menu();
        Day day = new WeekDay(DayOfWeek.MONDAY);
        Day day2 = new WeekDay(DayOfWeek.THURSDAY);
        day.add(recipe);
        day.add(recipe);
        day2.add(recipe);
        menu.add(day);
        menu.add(day2);
        Button testRecipeSearch = new Button("open search menu");
        testRecipeSearch.setOnAction(e -> showRecipeSearchStage());
        VBox vBox = new VBox();
        vBox.getChildren().addAll(testRecipeSearch, new WeeklyMenuBox(menu));
        return vBox;
    }

    private static void showRecipeSearchStage() {
        Dialog<Recipe> recipeSearchStage = new RecipeAddMenu();
        recipeSearchStage.show();
        Recipe result = recipeSearchStage.getResult();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
