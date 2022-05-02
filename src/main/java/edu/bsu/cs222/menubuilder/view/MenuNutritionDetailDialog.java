package edu.bsu.cs222.menubuilder.view;

import edu.bsu.cs222.menubuilder.model.Menu;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

public class MenuNutritionDetailDialog extends Stage {
    public MenuNutritionDetailDialog(Menu menu) {
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setLabel("Nutrients");
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setLabel("% Daily Values");
        BarChart<Number, String> chart = new BarChart<>(numberAxis, categoryAxis);
        chart.setLegendVisible(false);
        chart.setData(menu.generateObservableList());
        this.setMinHeight(640);
        this.setScene(new Scene(chart));
    }
}
