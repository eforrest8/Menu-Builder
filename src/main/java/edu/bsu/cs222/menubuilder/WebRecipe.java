package edu.bsu.cs222.menubuilder;

import java.net.*;

public class WebRecipe implements RemoteRecipe {
    private String title = "test default title xd";
    private URL recipeURL;

    public WebRecipe(String url) {
        setRecipeURL(url);
    }

    @Override
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

    @Override
    public URI getURI() {
        try {
            return recipeURL.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return URI.create("");
    }
}
