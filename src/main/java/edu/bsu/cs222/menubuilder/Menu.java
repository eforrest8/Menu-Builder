package edu.bsu.cs222.menubuilder;

import java.util.LinkedList;
import java.util.List;

public record Menu(List<WeekDay> days) {
    public Menu() {
        this(new LinkedList<>(List.of()));
    }
}
