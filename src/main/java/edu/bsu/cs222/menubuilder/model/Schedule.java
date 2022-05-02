package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Schedule(@JsonProperty List<Menu> menus) {

    @Override
    public List<Menu> menus() {
        return List.copyOf(menus);
    }

    public Schedule copy() {
        return new Schedule(menus.stream().map(Menu::copy).toList());
    }

}
