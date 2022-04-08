package edu.bsu.cs222.menubuilder;

//TO DO
// Add recipe and take away recipe buttons
// Close and save button for the editor
// Buttons to change

import javafx.scene.layout.HBox;

public class MenuEditor extends HBox {
    private final Menu menu;
    public MenuEditor(Menu menu){
        this.menu = menu;
    }
    private void buildUI() {
        this.setMinSize(640, 480);
        for (Day day: menu) {
            this.getChildren().add(new DayViewBox(day));

        }
    }
}