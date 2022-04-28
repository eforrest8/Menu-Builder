package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class ScheduleDeserializerTest {

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
    public void parse() {
        ScheduleDeserializer parser = new ScheduleDeserializer();
        Reader reader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("savedmenu.json")));
        Schedule actual = parser.deserialize(reader);
        Schedule expected = initializeMenu();
        Assertions.assertEquals(expected, actual);
    }
}