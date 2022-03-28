package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.util.LinkedList;

public class WeekDay extends LinkedList<Recipe> implements Day {
    private DayOfWeek dayOfWeek;

    public WeekDay(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getName() {
        return dayOfWeek.name();
    }

    @Override
    public int getIndex() {
        return dayOfWeek.getValue();
    }
}
