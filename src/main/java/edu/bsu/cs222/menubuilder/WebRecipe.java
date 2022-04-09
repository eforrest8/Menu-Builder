package edu.bsu.cs222.menubuilder;

import java.net.*;

public class WebRecipe {
    private final String title;
    private URL recipeURL;

    public WebRecipe(String title, String url) {
        this.title = title;
        setRecipeURL(url);
    }

    public String getTitle() {
        return title;
    }

    public void setRecipeURL(String url) {
        try {
            recipeURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public URI getURI() {
        try {
            return recipeURL.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return URI.create("");
    }

}
