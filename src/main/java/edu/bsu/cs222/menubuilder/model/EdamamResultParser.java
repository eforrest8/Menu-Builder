package edu.bsu.cs222.menubuilder.model;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

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
            Map<String, Map<String, Object>> totalInfo = context.read("$.hits[" + i + "].recipe.totalNutrients");
            Map<String, Map<String, Object>> dailyInfo = context.read("$.hits[" + i + "].recipe.totalDaily");
            Map<String, NutrientInfo> totalNutrientInfoMap = new HashMap<>();
            Map<String, NutrientInfo> dailyValueInfoMap = new HashMap<>();
            totalInfo.forEach((key, value) -> {
                if (value.get("quantity") instanceof Double quantity) {
                    totalNutrientInfoMap.put(key,
                            new NutrientInfo(
                                    (String) value.get("label"),
                                    quantity,
                                    (String) value.get("unit")));
                }
            });
            dailyInfo.forEach((key, value) -> {
                if (value.get("quantity") instanceof Double quantity) {
                    dailyValueInfoMap.put(key,
                            new NutrientInfo(
                                    (String) value.get("label"),
                                    quantity,
                                    (String) value.get("unit")));
                }
            });
            recipe.setTotalNutrients(totalNutrientInfoMap);
            recipe.setTotalDaily(dailyValueInfoMap);
            recipes.add(recipe);
        }
        return recipes;
    }
}