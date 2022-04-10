package edu.bsu.cs222.menubuilder;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class SavedMenuParser {
    public Menu parse(String jsonString) {
        DocumentContext context = JsonPath.using(Configuration.defaultConfiguration())
                .parse(jsonString);


        for (int i = 0; i < context.read("$.days.length()", Integer.class); i++) {

        }

        return result;
    }

}
