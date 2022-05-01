package edu.bsu.cs222.menubuilder.model;

import java.io.IOException;
import java.util.List;

public class EdamamSearchProvider {
    private final EdamamResultParser edamamResultParser = new EdamamResultParser();

    public List<WebRecipe> search(EdamamQuery query) throws IOException {
        EdamamUrl edamamUrl = new EdamamUrl(query);
        return edamamResultParser.parse(edamamUrl.getURL().openConnection().getInputStream());
    }
}

// Utilizes "edamamResultParser" class functions to build the backbone of the search function