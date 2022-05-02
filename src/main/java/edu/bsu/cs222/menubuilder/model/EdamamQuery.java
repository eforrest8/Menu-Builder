package edu.bsu.cs222.menubuilder.model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EdamamQuery {

    private final String searchText;

    public EdamamQuery(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return URLEncoder.encode(searchText, StandardCharsets.UTF_8);
    }
}
