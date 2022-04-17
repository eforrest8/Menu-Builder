package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.*;
import java.util.Objects;

public class WebRecipe {

    @JsonProperty
    private final String title;
    @JsonProperty
    private URL recipeURL;

    @JsonCreator
    public WebRecipe(@JsonProperty("title") String title, @JsonProperty("recipeURL") String url) {
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

    @JsonIgnore
    public URI getURI() {
        try {
            return recipeURL.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return URI.create("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebRecipe webRecipe = (WebRecipe) o;
        return title.equals(webRecipe.title) && recipeURL.equals(webRecipe.recipeURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, recipeURL);
    }

    @Override
    public String toString() {
        return "WebRecipe{" +
                "title='" + title + '\'' +
                ", recipeURL=" + recipeURL +
                '}';
    }
}
