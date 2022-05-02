package edu.bsu.cs222.menubuilder.model;

import java.io.*;
import java.util.Objects;

public class EdamamApiProvider {

    private final String appId;
    private final String apiKey;

    public EdamamApiProvider() throws IOException {
        // Pulls and inputs API data to access Edamam database
        InputStream apiInput = getClass().getClassLoader().getResourceAsStream("api.txt");
        Objects.requireNonNull(apiInput);
        try (BufferedReader apiReader = new BufferedReader(new InputStreamReader(apiInput))) {
            appId = apiReader.readLine();
            apiKey = apiReader.readLine();
        }
    }

    public String getAppId() {
        return appId;
    }

    public String getApiKey() {
        return apiKey;
    }
}
