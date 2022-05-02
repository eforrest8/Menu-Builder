package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MenuViewBox extends VBox {

    public MenuViewBox(Menu day) {
        buildUI(day);
    }

    private void buildUI(Menu day) {
        configure();
        addChildren(day);
    }

    private void addChildren(Menu day) {
        this.getChildren().add(buildNameLabel(day));
        for (WebRecipe recipe: day.getRecipes()) {
            this.getChildren().add(new RecipeViewBox(recipe));
        }
        this.getChildren().add(buildDetailButton(day));
    }

    private Button buildDetailButton(Menu menu) {
        Button button = new Button("See nutrition details");
        button.setOnAction(e -> new MenuNutritionDetailDialog(menu).showAndWait());
        return button;
    }

    private Label buildNameLabel(Menu day) {
        Label label = new Label(day.getName());
        label.setAlignment(Pos.TOP_CENTER);
        return label;
    }

    private void configure() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setMinWidth(112);
        this.setPadding(new Insets(2));
        this.setSpacing(2);
        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, BorderStroke.DEFAULT_WIDTHS)));
    }
}
