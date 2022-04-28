package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScheduleSerializerTest {

    private final ScheduleSerializer serializer = new ScheduleSerializer(initializeMenu());

    private Schedule initializeMenu() {
        Map<String, NutrientInfo> nutrientMap = Map.of("nutrient", new NutrientInfo("label", 10, "unit"));
        WebRecipe first = new WebRecipe("first recipe", "https://example.com");
        first.setTotalNutrients(nutrientMap);
        first.setTotalDaily(nutrientMap);
        WebRecipe second = new WebRecipe("second recipe", "https://example.com");
        second.setTotalNutrients(nutrientMap);
        second.setTotalDaily(nutrientMap);
        WebRecipe third = new WebRecipe("third recipe", "https://example.com");
        third.setTotalNutrients(nutrientMap);
        third.setTotalDaily(nutrientMap);
        WebRecipe fourth = new WebRecipe("fourth recipe", "http://example.com");
        fourth.setTotalNutrients(nutrientMap);
        fourth.setTotalDaily(nutrientMap);
        Menu monday = new Menu(DayOfWeek.MONDAY, List.of(first, second));
        Menu tuesday = new Menu(DayOfWeek.TUESDAY, List.of(third, fourth));
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
