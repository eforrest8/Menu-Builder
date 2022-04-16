package edu.bsu.cs222.menubuilder.model;

public final class MenuSingleton {
    public static Schedule schedule = new WeeklyScheduleBuilder().buildWeeklySchedule();

    private MenuSingleton() {}
}
