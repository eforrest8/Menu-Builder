package edu.bsu.cs222.menubuilder;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;

public class SavedMenuParser {
    public Menu parse(String jsonString) {
        DocumentContext context = JsonPath.using(Configuration.defaultConfiguration())
                .parse(jsonString);

        Menu result = new Menu();
        for (int i = 0; i < context.read("$.days.length()", Integer.class); i++) {
            List<WebRecipe> recipeList = new LinkedList<>();
            for (int j = 0; j < context.read("$.days[" + i + "].recipes.length()", Integer.class); j++) {
                String title = context.read("$.days[" + i + "].recipes[" + j + "].title", String.class);
                String url = context.read("$.days[" + i + "].recipes[" + j + "].url", String.class);
                recipeList.add(new WebRecipe(title, url));
            }
            String name = context.read("$.days[" + i + "].name", String.class);
            result.days().add(new WeekDay(DayOfWeek.valueOf(name), recipeList));
        }

        return result;
    }

}
