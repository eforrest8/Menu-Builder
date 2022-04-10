package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;

public record WeekDay(DayOfWeek dayOfWeek, List<WebRecipe> recipes) {
    public WeekDay(DayOfWeek dayOfWeek) {
        this(dayOfWeek, new LinkedList<>(List.of()));
    }

    public String getName() {
        return dayOfWeek().name();
    }
}
