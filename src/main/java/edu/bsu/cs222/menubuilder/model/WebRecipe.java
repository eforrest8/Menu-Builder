package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
        return title.equals(webRecipe.title) && recipeURL.equals(webRecipe.recipeURL)
                && mapEquals(totalDaily, webRecipe.totalDaily)
                && mapEquals(totalNutrients, webRecipe.totalNutrients);
    }

    private boolean mapEquals(Map<String, NutrientInfo> first, Map<String, NutrientInfo> second) {
        return first.entrySet().stream()
                .allMatch(entry -> second.containsKey(entry.getKey()) &&
                        second.get(entry.getKey()).equals(entry.getValue()));
    }

    @Override
    public String toString() {
        return "WebRecipe{\n" +
                "title='" + title + '\'' +
                ",\nrecipeURL=" + recipeURL +
                ",\ntotalNutrients=" + totalNutrients +
                ",\ntotalDaily=" + totalDaily +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, recipeURL, totalDaily, totalNutrients);
    }

    public NutrientInfo getNutrientValue(String nutrient) {
        return totalNutrients.get(nutrient);
    }

    public NutrientInfo getDailyValue(String nutrient) {
        return totalDaily.get(nutrient);
    }

    @JsonIgnore
    public Set<String> getNutrientKeySet() {
        return totalNutrients.keySet();
    }

    @JsonIgnore
    public Set<String> getDailyValueKeySet() {
        return totalDaily.keySet();
    }
}
