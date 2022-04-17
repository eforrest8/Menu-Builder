package edu.bsu.cs222.menubuilder.model;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EdamamSearchProvider {

    public List<WebRecipe> search(EdamamQuery query) throws IOException {
        EdamamUrl edamamUrl = new EdamamUrl(query);
        DocumentContext context = JsonPath.parse(edamamUrl.getURL().openConnection().getInputStream());
        List<Map<String, String>> list = context.read("$.hits.*.recipe");
        return list.stream().map(map -> new WebRecipe(map.get("label"), map.get("shareAs"))).toList();
    }

}