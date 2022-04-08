package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;

public class WeeklyMenu extends Menu {

    List<DayOfWeek> daysOfWeek = List.of(DayOfWeek.values());

    public WeeklyMenu() {
        int offset = Integer.parseInt(
                Preferences.userNodeForPackage(MenuBuilderApplication.class).get("WEEK_START_OFFSET", "0"));
        Collections.rotate(daysOfWeek, offset);
        this.addAll(daysOfWeek.stream().sequential()
                .map(WeekDay::new)
                .toList());
    }

}
