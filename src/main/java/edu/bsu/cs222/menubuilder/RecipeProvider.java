package edu.bsu.cs222.menubuilder;

import java.util.List;

public interface RecipeProvider {
    List<Recipe> search(String query);
}
