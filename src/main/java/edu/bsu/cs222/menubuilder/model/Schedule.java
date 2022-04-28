package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Schedule {

    @JsonProperty
    private final List<Menu> menus;

    public List<Menu> getMenus() {
        return List.copyOf(menus);
    }

    @JsonCreator
    public Schedule(@JsonProperty("menus") List<Menu> menus) {
        this.menus = menus;
    }

    public Schedule deepCopy() {
        return new Schedule(menus.stream().map(Menu::copy).toList());
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

}
