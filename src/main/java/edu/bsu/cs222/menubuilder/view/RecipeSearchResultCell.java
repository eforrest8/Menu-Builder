package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.WebRecipe;
import javafx.scene.control.ListCell;

public class RecipeSearchResultCell extends ListCell<WebRecipe> {

    @Override
    protected void updateItem(WebRecipe item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getTitle());
        }
    }

}
