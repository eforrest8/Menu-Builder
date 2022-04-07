package edu.bsu.cs222.menubuilder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.time.DayOfWeek;

public class MenuBuilderApplication extends Application {
    private final static Recipe recipe = new WebRecipe("corned beef and cabbage", "https://www.allrecipes.com/recipe/16310/corned-beef-and-cabbage-i/");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(buildUI());
        primaryStage.show();
    }

    private static Scene buildUI() {
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
        return new WeeklyMenuViewScene(menu);
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
