package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.MenuSerializer;
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

public class ScheduleSerializerTest {

    final MenuSerializer serializer = new MenuSerializer(initializeMenu());

    private Schedule initializeMenu() {
        Schedule schedule = new Schedule();
        Menu monday = new Menu(DayOfWeek.MONDAY);
        Menu tuesday = new Menu(DayOfWeek.TUESDAY);
        WebRecipe google = new WebRecipe("google", "https://google.com");
        WebRecipe bing = new WebRecipe("binf", "https://bing.con");
        WebRecipe ddg = new WebRecipe("duckduckgo", "https://yahoo.com");
        WebRecipe askjeeves = new WebRecipe("askjeeves", "http://askjeeves.net");
        monday.getRecipes().addAll(List.of(google, bing));
        tuesday.getRecipes().addAll(List.of(ddg, askjeeves));
        schedule.getDays().addAll(List.of(monday, tuesday));
        return schedule;
    }

    @Test
    public void testWebRecipe() throws URISyntaxException, IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("savedmenu.json")).toURI());
        String expected = Files.readString(file.toPath());
        String actual = serializer.serialize();
        Assertions.assertEquals(expected, actual);
    }
}
