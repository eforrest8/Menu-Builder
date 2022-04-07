package edu.bsu.cs222.menubuilder;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class RecipeSearchView extends VBox {
    private final VBox searchResults = new VBox();

    public RecipeSearchView() {
        buildUI();
    }

    private void buildUI() {
        this.getChildren().addAll(
                buildInputBar(),
                searchResults);
        this.setMinSize(256, 512);
    }

    private HBox buildInputBar() {
        TextField searchInput = new TextField();
        searchInput.setPromptText("Enter search query");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> performSearch(searchInput.getText()));
        HBox inputBar = new HBox();
        inputBar.getChildren().addAll(searchInput, searchButton);
        return inputBar;
    }

    private void performSearch(String searchQuery) {
        RecipeProvider webProvider = new EdamamApiProvider();
        List<Recipe> result = webProvider.search(searchQuery);
        updateSearchResults(result);
    }

    private void updateSearchResults(List<Recipe> result) {
        searchResults.getChildren().clear();
        for (Recipe recipe :
                result) {
            searchResults.getChildren()
                    .add(new RecipeDetailView(recipe));
        }
    }
}
