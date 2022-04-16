package edu.bsu.cs222.menubuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Schedule {
    public List<Menu> getDays() {
        return List.copyOf(days);
    }

    private final List<Menu> days;

    public Schedule() {
        this(new LinkedList<>(List.of()));
    }

    public Schedule(List<Menu> days) {
        this.days = days;
    }

    public void addDay(Menu day) {
        days.add(day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(days, schedule.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days);
    }
}
