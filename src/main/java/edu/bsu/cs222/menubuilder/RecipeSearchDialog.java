package edu.bsu.cs222.menubuilder;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class RecipeSearchDialog extends Dialog<Recipe> {
    private final DialogPane pane = new DialogPane();
    private final ListView<Recipe> searchResults = new ListView<>();
    private final TextField searchInput = new TextField();

    public RecipeSearchDialog() {
        buildUI();
    }

    private void buildUI() {
        searchResults.getSelectionModel().selectedIndexProperty().addListener(this::listenToSelection);
        searchResults.setCellFactory( listView -> new RecipeSearchResultCell());
        searchResults.setMinSize(256, 256);
        ButtonType okButtonType = new ButtonType("Choose this recipe", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        VBox.setVgrow(searchResults, Priority.ALWAYS);
        VBox.setMargin(searchResults, new Insets(4));
        pane.getButtonTypes().addAll(okButtonType, cancelButtonType);
        pane.setMinSize(512, 512);
        pane.getChildren().add(
                new VBox(
                        buildInputBar(),
                        searchResults));
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
        return searchResults.getSelectionModel().getSelectedItem();
    }

    private HBox buildInputBar() {
        searchInput.setMinWidth(196);
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
        List<Recipe> recipeCells = new EdamamApiProvider().search(searchInput.getText());
        searchResults.setItems(FXCollections.observableList(recipeCells));
    }

}
