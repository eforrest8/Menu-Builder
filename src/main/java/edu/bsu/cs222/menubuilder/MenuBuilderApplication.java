package edu.bsu.cs222.menubuilder;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.DayOfWeek;

public class MenuBuilderApplication extends Application {
    private static Recipe recipe = new WebRecipe("https://www.allrecipes.com/recipe/16310/corned-beef-and-cabbage-i/");

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
        return new WeeklyMenuBox(menu);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
