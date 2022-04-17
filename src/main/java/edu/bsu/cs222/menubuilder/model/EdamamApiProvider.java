package edu.bsu.cs222.menubuilder.model;

import com.jayway.jsonpath.*;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EdamamApiProvider {

    private static final String BASE_URL = "https://api.edamam.com/api/recipes/v2?type=public";
    private String appId;
    private String apiKey;
    private String partialUrl;

    public EdamamApiProvider() {
        loadApiKeys();
        buildPartialUrlString();
    }

    private void loadApiKeys() {
        InputStream apiInput = getClass().getClassLoader().getResourceAsStream("api.txt");
        Objects.requireNonNull(apiInput);
        try (BufferedReader apiReader = new BufferedReader(new InputStreamReader(apiInput))) {
            appId = apiReader.readLine();
            apiKey = apiReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<WebRecipe> search(String query) {
        appendQueryToPartialUrl(URLEncoder.encode(query, StandardCharsets.UTF_8));
        String finalUrl = BASE_URL + partialUrl;
        try {
            DocumentContext context = JsonPath.parse(new URL(finalUrl).openConnection().getInputStream());
            List<Map<String, String>> list = context.read("$.hits.*.recipe");
            return list.stream().map( map -> new WebRecipe(map.get("label"), map.get("shareAs"))).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private void buildPartialUrlString() {
        partialUrl =
                "&app_id=" + appId +
                "&app_key=" + apiKey +
                "&field=label" +
                "&field=shareAs";
    }

    private void appendQueryToPartialUrl(String query) {
        partialUrl = partialUrl + "&q=" + query;
    }

}
