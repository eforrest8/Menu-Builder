package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public record WeekDay(DayOfWeek dayOfWeek, List<WebRecipe> recipes) {
    public WeekDay(DayOfWeek dayOfWeek) {
        this(dayOfWeek, new LinkedList<>(List.of()));
    }

    public String getName() {
        return dayOfWeek().name();
    }
}
