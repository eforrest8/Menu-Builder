package edu.bsu.cs222.menubuilder.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EdamamUrl {
    private static final String BASE_URL = "https://api.edamam.com/api/recipes/v2?type=public";
    private static final String FIELDS = "&field=label&field=shareAs&field=totalNutrients&field=totalDaily";
    private final EdamamApiProvider apiProvider = new EdamamApiProvider();
    private final EdamamQuery query;

    public EdamamUrl(EdamamQuery query) throws IOException {
        this.query = query;
    }

    public URL getURL() throws MalformedURLException {
        return new URL(
                BASE_URL +
                        getAppIdPhrase() +
                        getApiKeyPhrase() +
                        FIELDS +
                        getQueryPhrase()
        );
    }

    private String getAppIdPhrase() {
        return "&app_id=" + apiProvider.getAppId();
    }

    private String getApiKeyPhrase() {
        return "&app_key=" + apiProvider.getApiKey();
    }

    private String getQueryPhrase() {
        return "&q=" + query.getSearchText();
    }
}