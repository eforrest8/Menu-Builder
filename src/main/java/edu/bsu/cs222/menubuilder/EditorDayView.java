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

public class EditorDayView extends VBox{
    private final Day day;

    public EditorDayView(Day day) {
        this.day = day;
        buildUI();
    }

    private void buildUI() {
        this.setMinSize(96, 512);
        this.getChildren().addAll(
                new Label(day.getName()),
                new Label(Integer.toString(day.getIndex()))
        );
        for (Recipe recipe :
                day) {
            this.getChildren().add(new RecipeDetailView(recipe));
            Button button1 = new Button("Open Editor");
            button1.setOnAction(EditorDayView(day, day));
        }
    }
    private void setButtonFunctions(){

    }
}

