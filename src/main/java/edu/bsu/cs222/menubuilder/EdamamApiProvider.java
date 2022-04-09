package edu.bsu.cs222.menubuilder;

import java.io.*;
import java.util.List;

public class EdamamApiProvider implements RecipeProvider {
    private String appId;
    private String apiKey;

    public EdamamApiProvider() {
        getApiData();
    }

    @Override
    public List<Recipe> search(String query) {
        return List.of(
                new WebRecipe("google", "https://google.com"),
                new WebRecipe("binf", "https://bing.com"),
                new WebRecipe("has the large hadron collider destroyed the world yet?",
                        "http://hasthelargehadroncolliderdestroyedtheworldyet.com/")
                );
    }

    private void getApiData() {
        InputStream apiInput = getClass().getClassLoader().getResourceAsStream("api.txt");
        assert apiInput != null;
        try (BufferedReader apiReader = new BufferedReader(new InputStreamReader(apiInput))) {
            appId = apiReader.readLine();
            apiKey = apiReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
