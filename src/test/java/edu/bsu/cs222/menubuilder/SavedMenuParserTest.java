package edu.bsu.cs222.menubuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

class SavedMenuParserTest {

    private Menu initializeMenu() {
        Menu menu = new Menu();
        WeekDay monday = new WeekDay(DayOfWeek.MONDAY);
        WeekDay tuesday = new WeekDay(DayOfWeek.TUESDAY);
        WebRecipe google = new WebRecipe("google", "https://google.com");
        WebRecipe bing = new WebRecipe("binf", "https://bing.con");
        WebRecipe ddg = new WebRecipe("duckduckgo", "https://yahoo.com");
        WebRecipe askjeeves = new WebRecipe("askjeeves", "http://askjeeves.net");
        monday.recipes().addAll(List.of(google, bing));
        tuesday.recipes().addAll(List.of(ddg, askjeeves));
        menu.days().addAll(List.of(monday, tuesday));
        return menu;
    }

    @Test
    void parse() throws IOException, URISyntaxException {
        SavedMenuParser parser = new SavedMenuParser();
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("savedmenu.json")).toURI());
        Menu actual = parser.parse(Files.readString(file.toPath()));
        Menu expected = initializeMenu();
        Assertions.assertEquals(expected, actual);
    }
}