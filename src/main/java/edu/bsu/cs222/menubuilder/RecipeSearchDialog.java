package edu.bsu.cs222.menubuilder;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.Optional;

public class RecipeSearchDialog extends Dialog<Recipe> {
    private final ListView<RecipeSearchResultCell> searchResults = new ListView<>();
    private final DialogPane pane = new DialogPane();
    private final TextField searchInput = new TextField();

    public RecipeSearchDialog() {
        buildUI();
    }

    private void buildUI() {
        searchResults.getSelectionModel().selectedIndexProperty().addListener(this::listenToSelection);
        searchResults.setMinSize(256, 256);
        ButtonType okButtonType = new ButtonType("Choose this recipe", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        pane.getButtonTypes().addAll(okButtonType, cancelButtonType);
        pane.getChildren().addAll(
                buildInputBar(),
                searchResults);
        pane.setMinSize(256, 512);
        this.setDialogPane(pane);
        this.setResultConverter(this::resultConverter);
    }

    private void listenToSelection(Observable observable) {
        pane.setDisable(observable == null);
    }

    private Recipe resultConverter(ButtonType buttonType) {
        if (searchResults.getSelectionModel().getSelectedItem() == null
                || buttonType.getButtonData().isCancelButton()) {
            return null;
        }
        return searchResults.getSelectionModel().getSelectedItem().getItem();
    }

    private HBox buildInputBar() {
        searchInput.setMinWidth(128);
        searchInput.setPromptText("Enter search query");
        Button searchButton = new Button("Search");
        searchButton.setDefaultButton(true);
        searchButton.setMinWidth(64);
        searchButton.setOnAction(this::updateSearchResults);
        HBox inputBar = new HBox();
        inputBar.getChildren().addAll(searchInput, searchButton);
        return inputBar;
    }

    private void updateSearchResults(Event event) {
        List<RecipeSearchResultCell> recipeCells = new EdamamApiProvider()
                .search(searchInput.getText())
                .stream()
                .map(RecipeSearchResultCell::new)
                .toList();
        searchResults.setItems(FXCollections.observableList(recipeCells));
    }

}
