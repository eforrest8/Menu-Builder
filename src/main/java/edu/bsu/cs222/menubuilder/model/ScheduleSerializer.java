package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Writer;

public class ScheduleSerializer {

    private final Schedule schedule;

    private Menu currentDay;

    public ScheduleSerializer(Schedule schedule) {
        this.schedule = schedule;
    }

    public void serialize(Writer writer) {
        JsonFactory factory = new JsonFactory();
        try {
            factory.createGenerator(writer).setCodec(new ObjectMapper()).writePOJO(schedule);
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }

    public String oldSerialize() {
        return "{\"days\": [" + schedule.getMenus().stream().collect(StringBuilder::new, this::serializeDay, StringBuilder::append) + "]}";
    }

    private void serializeDay(StringBuilder stringBuilder, Menu day) {
        currentDay = day;
        stringBuilder.append("{")
                .append("\"name\": \"").append(day.getName()).append("\",")
                .append("\"recipes\": [")
                .append(day.getRecipes().stream().collect(StringBuilder::new, this::serializeRecipe, StringBuilder::append))
                .append("]}")
                .append(schedule.getMenus().indexOf(day) == schedule.getMenus().size() - 1 ? "" : ",");
    }

    private void serializeRecipe(StringBuilder stringBuilder, WebRecipe webRecipe) {
        stringBuilder.append("{")
                .append("\"title\": \"").append(webRecipe.getTitle()).append("\",")
                .append("\"url\": \"").append(webRecipe.getURI()).append("\"")
                .append("}")
                .append(currentDay.getRecipes().indexOf(webRecipe) == currentDay.getRecipes().size() - 1 ? "" : ",");
    }
}
