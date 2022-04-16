package edu.bsu.cs222.menubuilder.model;

public class MenuSerializer {

    private final Schedule schedule;

    private Menu currentDay;

    public MenuSerializer(Schedule schedule) {
        this.schedule = schedule;
    }

    public String serialize() {
        return "{\"days\": [" + schedule.getDays().stream().collect(StringBuilder::new, this::serializeDay, StringBuilder::append) + "]}";
    }

    private void serializeDay(StringBuilder stringBuilder, Menu day) {
        currentDay = day;
        stringBuilder.append("{")
                .append("\"name\": \"").append(day.getName()).append("\",")
                .append("\"recipes\": [")
                .append(day.getRecipes().stream().collect(StringBuilder::new, this::serializeRecipe, StringBuilder::append))
                .append("]}")
                .append(schedule.getDays().indexOf(day) == schedule.getDays().size() - 1 ? "" : ",");
    }

    private void serializeRecipe(StringBuilder stringBuilder, WebRecipe webRecipe) {
        stringBuilder.append("{")
                .append("\"title\": \"").append(webRecipe.getTitle()).append("\",")
                .append("\"url\": \"").append(webRecipe.getURI()).append("\"")
                .append("}")
                .append(currentDay.getRecipes().indexOf(webRecipe) == currentDay.getRecipes().size() - 1 ? "" : ",");
    }
}
