package edu.bsu.cs222.menubuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

public class MenuSerializerTest {

    final MenuSerializer serializer = new MenuSerializer(initializeMenu());

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
    public void testWebRecipe() throws URISyntaxException, IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("savedmenu.json")).toURI());
        String expected = Files.readString(file.toPath());
        String actual = serializer.serialize();
        Assertions.assertEquals(expected, actual);
    }
}
