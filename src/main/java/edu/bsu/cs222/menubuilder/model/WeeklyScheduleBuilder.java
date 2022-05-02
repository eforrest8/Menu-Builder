package edu.bsu.cs222.menubuilder.model;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;

public class WeeklyScheduleBuilder {

    private final List<DayOfWeek> daysOfWeek = new LinkedList<>(List.of(DayOfWeek.values()));

    public Schedule buildWeeklySchedule() {
        return new Schedule(daysOfWeek.stream().sequential()
                .map(Menu::new)
                .toList());
    }

}
