package edu.bsu.cs222.menubuilder;

import java.util.List;

public class EdamamApiProvider implements RecipeProvider {
    @Override
    public List<Recipe> search(String query) {
        return List.of(new WebRecipe("google", "https://google.com"));
    }
}
