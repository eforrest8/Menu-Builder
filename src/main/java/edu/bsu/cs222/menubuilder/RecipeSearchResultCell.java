package edu.bsu.cs222.menubuilder;

import javafx.scene.control.ListCell;

public class RecipeSearchResultCell extends ListCell<Recipe> {

    public RecipeSearchResultCell(Recipe recipe) {
        super();
        updateItem(recipe, false);
    }

    @Override
    protected void updateItem(Recipe item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getTitle());
        }
    }

}
