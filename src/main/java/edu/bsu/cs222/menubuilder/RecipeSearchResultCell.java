package edu.bsu.cs222.menubuilder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class RecipeSearchResultCell extends ListCell<Recipe> {

    @Override
    protected void updateItem(Recipe item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getTitle());
        }
    }

}
