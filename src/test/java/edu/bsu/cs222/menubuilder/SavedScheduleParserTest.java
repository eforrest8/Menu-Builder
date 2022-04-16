package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.SavedMenuParser;
import edu.bsu.cs222.menubuilder.model.Schedule;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

class SavedScheduleParserTest {

    private Schedule initializeMenu() {
        Schedule schedule = new Schedule();
        Menu monday = new Menu(DayOfWeek.MONDAY);
        Menu tuesday = new Menu(DayOfWeek.TUESDAY);
        WebRecipe google = new WebRecipe("google", "https://google.com");
        WebRecipe bing = new WebRecipe("bing", "https://bing.con");
        WebRecipe ddg = new WebRecipe("duckduckgo", "https://duckduckgo.com");
        WebRecipe askjeeves = new WebRecipe("askjeeves", "http://askjeeves.net");
        monday.getRecipes().addAll(List.of(google, bing));
        tuesday.getRecipes().addAll(List.of(ddg, askjeeves));
        schedule.getDays().addAll(List.of(monday, tuesday));
        return schedule;
    }

    @Test
    void parse() throws IOException, URISyntaxException {
        SavedMenuParser parser = new SavedMenuParser();
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("savedmenu.json")).toURI());
        Schedule actual = parser.parse(Files.readString(file.toPath()));
        Schedule expected = initializeMenu();
        Assertions.assertEquals(expected, actual);
    }
}