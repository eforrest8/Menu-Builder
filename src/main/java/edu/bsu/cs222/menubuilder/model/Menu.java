package edu.bsu.cs222.menubuilder.model;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Menu {
    private final DayOfWeek dayOfWeek;

    public List<WebRecipe> getRecipes() {
        return List.copyOf(recipes);
    }

    private final List<WebRecipe> recipes;

    public Menu(DayOfWeek dayOfWeek, List<WebRecipe> recipes) {
        this.dayOfWeek = dayOfWeek;
        this.recipes = new LinkedList<>(recipes);
    }

    public Menu(DayOfWeek dayOfWeek) {
        this(dayOfWeek, new LinkedList<>(List.of()));
    }

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
        shiftRecipeUp(recipes.indexOf(recipe));
    }

    public void shiftRecipeUp(int index) {
        if (index > 0) {
            Collections.swap(recipes, index, index - 1);
        }
    }

    public void shiftRecipeDown(WebRecipe recipe) {
        shiftRecipeDown(recipes.indexOf(recipe));
    }

    public void shiftRecipeDown(int index) {
        if (index < recipes.size() - 1) {
            Collections.swap(recipes, index, index + 1);
        }
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

    @Override
    public String toString() {
        return "Menu[" +
                "dayOfWeek=" + dayOfWeek + ", " +
                "recipes=" + recipes + ']';
    }

}
