package edu.bsu.cs222.menubuilder;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class WebRecipe implements Recipe {
    private String title;
    private URI recipeURI;

    public WebRecipe(String url) {
        setRecipeURI(url);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setRecipeURI(String url) {
        try {
            recipeURI = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
