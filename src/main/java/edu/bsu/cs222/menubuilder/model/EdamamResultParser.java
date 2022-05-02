package edu.bsu.cs222.menubuilder.model;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EdamamResultParser {

    public List<WebRecipe> parse(InputStream stream) {
        DocumentContext context = JsonPath.parse(stream);
        List<WebRecipe> recipes = new LinkedList<>();
        int hits = context.read("$.hits.length()");
        for (int i = 0; i < hits; i++) {
            WebRecipe recipe = new WebRecipe(
                    context.read("$.hits[" + i + "].recipe.label"),
                    context.read("$.hits[" + i + "].recipe.shareAs")
            );
            Map<String, Map<String, Object>> totalInfo = context.read("$.hits[" + i + "].recipe.totalNutrients");
            Map<String, Map<String, Object>> dailyInfo = context.read("$.hits[" + i + "].recipe.totalDaily");
            recipe.setTotalNutrients(generateNutrientMap(totalInfo));
            recipe.setTotalDaily(generateNutrientMap(dailyInfo));
            recipes.add(recipe);
        }
        return recipes;
    }

    private Map<String, NutrientInfo> generateNutrientMap(Map<String, Map<String, Object>> initialMap) {
        Map<String, NutrientInfo> nutrientInfoMap = new HashMap<>();
        initialMap.forEach((key, value) -> {
            if (value.get("quantity") instanceof Double quantity) {
                nutrientInfoMap.put(key,
                        new NutrientInfo(
                                (String) value.get("label"),
                                quantity,
                                (String) value.get("unit")));
            }
        });
        return nutrientInfoMap;
    }

}