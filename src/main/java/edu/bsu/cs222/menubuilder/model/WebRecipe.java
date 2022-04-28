package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.*;

import java.net.*;
import java.util.Map;
import java.util.Objects;

public class WebRecipe {

    @JsonProperty
    private final String title;
    @JsonProperty
    private URL recipeURL;
    @JsonProperty
    private Map<String, NutrientInfo> totalNutrients;
    @JsonProperty
    private Map<String, NutrientInfo> totalDaily;

    public void setTotalNutrients(Map<String, NutrientInfo> totalInfo) {
        totalNutrients = totalInfo;
    }

    public void setTotalDaily(Map<String, NutrientInfo> dailyInfo) {
        totalDaily = dailyInfo;
    }

    @JsonCreator
    public WebRecipe(@JsonProperty("title") String title, @JsonProperty("recipeURL") String url) {
        this.title = title;
        setRecipeURL(url);
    }

    public String getTitle() {
        return title;
    }

    private void setRecipeURL(String url) {
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

    public NutrientInfo getNutrientValue(String nutrient) {
        return totalNutrients.get(nutrient);
    }

    public NutrientInfo getDailyValue(String nutrient) {
        return totalDaily.get(nutrient);
    }
}
