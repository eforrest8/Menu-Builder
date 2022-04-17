package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.Menu;
import edu.bsu.cs222.menubuilder.model.ScheduleSerializer;
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

    private final ScheduleSerializer serializer = new ScheduleSerializer(initializeMenu());

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
    public void testWebRecipe() throws URISyntaxException, IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("savedmenu.json")).toURI());
        String expected = Files.readString(file.toPath());
        StringWriter writer = new StringWriter();
        serializer.serialize(writer);
        String actual = writer.toString();
        Assertions.assertEquals(expected, actual);
    }
}
