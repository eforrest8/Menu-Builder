package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.LinkedList;

public class WeekDay extends LinkedList<Recipe> {
    private DayOfWeek dayOfWeek;

    public WeekDay(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getName() {
        return dayOfWeek.name();
    }
}
