package edu.bsu.cs222.menubuilder;

public final class MenuSingleton {
    public static Menu menu = new WeeklyMenu();

    private MenuSingleton() {}
}
