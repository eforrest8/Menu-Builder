package edu.bsu.cs222.menubuilder;

import java.time.DayOfWeek;

/**
 * This class is a singleton which represents all global data required
 * by the application. This includes the {@link Menu} currently loaded
 * as well as all user-defined settings.
 *
 * Only the absolute minimum of data should be included here!
 */
public final class UserData {

    private UserData() {}

    public static Menu currentMenu;

    //settings should go below this line
    public static DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
}
