package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.time.DayOfWeek;
import java.util.*;

public class Menu {

    @JsonProperty
    private final DayOfWeek dayOfWeek;
    @JsonProperty
    private final List<WebRecipe> recipes;

    @JsonCreator
    public Menu(@JsonProperty("dayOfWeek") DayOfWeek dayOfWeek, @JsonProperty("recipes") List<WebRecipe> recipes) {
        this.dayOfWeek = dayOfWeek;
        this.recipes = new LinkedList<>(recipes);
    }

    public Menu(DayOfWeek dayOfWeek) {
        this(dayOfWeek, new LinkedList<>(List.of()));
    }

    public List<WebRecipe> getRecipes() {
        return List.copyOf(recipes);
    }

    @JsonIgnore
    public String getName() {
        return dayOfWeek.name();
    }

    public void addRecipe(WebRecipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(WebRecipe recipe) {
        recipes.remove(recipe);
    }

    public void shiftRecipeUp(WebRecipe recipe) {
        int index = recipes.indexOf(recipe);
        if (index > 0) {
            Collections.swap(recipes, index, index - 1);
        }
    }

    public void shiftRecipeDown(WebRecipe recipe) {
        int index = recipes.indexOf(recipe);
        if (index < recipes.size() - 1) {
            Collections.swap(recipes, index, index + 1);
        }
    }

    public NutrientInfo getTotalNutrients(String nutrient) {
        Optional<NutrientInfo> result = recipes.stream()
                .map(recipe -> recipe.getNutrientValue(nutrient))
                .reduce(NutrientInfo::sumQuantities);
        return result.orElseThrow();
    }

    public NutrientInfo getTotalDailyValue(String nutrient) {
        Optional<NutrientInfo> result = recipes.stream()
                .map(recipe -> recipe.getDailyValue(nutrient))
                .reduce(NutrientInfo::sumQuantities);
        return result.orElseThrow();
    }

    public ObservableList<XYChart.Series<Number, String>> generateObservableList() {
        Set<String> keys = recipes.get(0).getDailyValueKeySet();
        return FXCollections.observableList(keys.stream()
                .map(this::getTotalDailyValue)
                .map(nutrientInfo -> List.of(new XYChart.Data<>((Number)nutrientInfo.quantity(), nutrientInfo.label())))
                .map(FXCollections::observableList)
                .map(XYChart.Series::new)
                .toList());
    }

    public Menu copy() {
        return new Menu(DayOfWeek.of(dayOfWeek.getValue()), getRecipes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return dayOfWeek == menu.dayOfWeek && recipes.equals(menu.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, recipes);
    }

}
