package edu.bsu.cs222.menubuilder.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EdamamQueryTest {

    @ParameterizedTest
    @CsvSource({"spaghetti,spaghetti", "ham sandwich,ham+sandwich"})
    void getSearchText(String search, String expected) {
        EdamamQuery query = new EdamamQuery(search);
        Assertions.assertEquals(expected, query.getSearchText());
    }
}