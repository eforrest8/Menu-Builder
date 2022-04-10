package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

public class WeeklyMenuBuilder {

    final List<DayOfWeek> daysOfWeek = new LinkedList<>(List.of(DayOfWeek.values()));

    public Menu buildWeeklyMenu() {
        int offset = Integer.parseInt(
                Preferences.userNodeForPackage(MenuBuilderApplication.class).get("WEEK_START_OFFSET", "0"));
        Collections.rotate(daysOfWeek, offset);
        return new Menu(daysOfWeek.stream().sequential()
                .map(WeekDay::new)
                .toList());
    }

}