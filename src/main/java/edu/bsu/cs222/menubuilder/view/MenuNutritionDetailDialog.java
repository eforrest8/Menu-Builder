package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

// Creates nutrient infographic

public class MenuNutritionDetailDialog extends Stage {
    public MenuNutritionDetailDialog(Menu menu) {
        BarChart<Number, String> chart = new BarChart<>(new NumberAxis(), new CategoryAxis());
        chart.setData(menu.generateObservableList());
        this.setScene(new Scene(chart));
    }
}
