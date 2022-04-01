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
        Day dayM = new WeekDay(DayOfWeek.MONDAY);
        Day dayT = new WeekDay(DayOfWeek.TUESDAY);
        Day dayW = new WeekDay(DayOfWeek.WEDNESDAY);
        Day dayTR = new WeekDay(DayOfWeek.THURSDAY);
        Day dayF = new WeekDay(DayOfWeek.FRIDAY);
        Day dayS = new WeekDay(DayOfWeek.SATURDAY);
        Day daySU = new WeekDay(DayOfWeek.SUNDAY);
        // Need below to be useable inside of an add class -AL
        dayM.add(recipe);
        dayT.add(recipe);
        dayW.add(recipe);
        dayTR.add(recipe);
        dayF.add(recipe);
        dayS.add(recipe);
        daySU.add(recipe);
        // Need to be able to pull from daily libraries -AL
        menu.add(dayM);
        menu.add(dayT);
        menu.add(dayW);
        menu.add(dayTR);
        menu.add(dayF);
        menu.add(dayS);
        menu.add(daySU);
        return new WeeklyMenuBox(menu);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
