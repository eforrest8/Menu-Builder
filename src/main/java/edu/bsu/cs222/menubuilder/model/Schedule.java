package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Schedule {

    @JsonProperty
    private final List<Menu> menus;

    public List<Menu> getMenus() {
        return List.copyOf(menus);
    }

    public Schedule(List<Menu> menus) {
        this.menus = menus;
    }

    public Schedule copy() {
        return new Schedule(getMenus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(menus, schedule.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menus);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "menus=" + menus +
                '}';
    }
}
