package edu.bsu.cs222.menubuilder.model;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EdamamResultParser {
    public EdamamResultParser() {
    }

    public List<WebRecipe> parse(InputStream stream) {
        DocumentContext context = JsonPath.parse(stream);
        List<WebRecipe> recipes = new LinkedList<>();
        int hits = context.read("$.hits.length()");
        for (int i = 0; i < hits; i++) {
            WebRecipe recipe = new WebRecipe(
                    context.read("$.hits[" + i + "].recipe.label"),
                    context.read("$.hits[" + i + "].recipe.shareAs")
            );
            Map<String, NutrientInfo> totalInfo = context.read("$.hits[" + i + "].recipe.totalNutrients");
            Map<String, NutrientInfo> dailyInfo = context.read("$.hits[" + i + "].recipe.totalDaily");
            recipe.setTotalNutrients(totalInfo);
            recipe.setTotalDaily(dailyInfo);
        }
        return recipes;

    }
}