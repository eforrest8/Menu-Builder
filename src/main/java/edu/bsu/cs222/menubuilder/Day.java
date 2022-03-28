package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.util.LinkedList;

public class Day extends LinkedList<Recipe> {
    private DayOfWeek dayOfWeek;

    public Day(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getName() {
        return dayOfWeek.name();
    }
}
