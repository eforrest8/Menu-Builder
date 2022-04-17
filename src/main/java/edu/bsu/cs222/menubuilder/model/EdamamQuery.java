package edu.bsu.cs222.menubuilder.model;

public class EdamamQuery {

    private final String searchText;

    public EdamamQuery(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }
}
