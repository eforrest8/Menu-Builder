package edu.bsu.cs222.menubuilder.model;

import edu.bsu.cs222.menubuilder.view.MenuBuilderApplication;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

public class WeeklyScheduleBuilder {

    private final List<DayOfWeek> daysOfWeek = new LinkedList<>(List.of(DayOfWeek.values()));

    public Schedule buildWeeklySchedule() {
        int offset = Integer.parseInt(
                Preferences.userNodeForPackage(MenuBuilderApplication.class).get("WEEK_START_OFFSET", "0"));
        Collections.rotate(daysOfWeek, offset);
        return new Schedule(daysOfWeek.stream().sequential()
                .map(Menu::new)
                .toList());
    }

}
