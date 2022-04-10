package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.Objects;

public class WeekDay extends LinkedList<WebRecipe> implements Day {
    private final DayOfWeek dayOfWeek;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WeekDay that = (WeekDay) o;
        return dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dayOfWeek);
    }
}
