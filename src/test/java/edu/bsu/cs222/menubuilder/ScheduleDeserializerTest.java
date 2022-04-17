package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.ScheduleDeserializer;
import edu.bsu.cs222.menubuilder.model.Schedule;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

class ScheduleDeserializerTest {

    private Schedule initializeMenu() {
        WebRecipe google = new WebRecipe("google", "https://google.com");
        WebRecipe bing = new WebRecipe("bing", "https://bing.com");
        WebRecipe ddg = new WebRecipe("duckduckgo", "https://duckduckgo.com");
        WebRecipe askjeeves = new WebRecipe("askjeeves", "http://askjeeves.net");
        Menu monday = new Menu(DayOfWeek.MONDAY, List.of(google, bing));
        Menu tuesday = new Menu(DayOfWeek.TUESDAY, List.of(ddg, askjeeves));
        return new Schedule(List.of(monday, tuesday));
    }

    @Test
    void parse() {
        ScheduleDeserializer parser = new ScheduleDeserializer();
        Reader reader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("savedmenu.json")));
        Schedule actual = parser.deserialize(reader);
        Schedule expected = initializeMenu();
        Assertions.assertEquals(expected, actual);
    }
}