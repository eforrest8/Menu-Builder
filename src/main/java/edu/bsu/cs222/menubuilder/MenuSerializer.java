package edu.bsu.cs222.menubuilder;

public class MenuSerializer {

    private final Menu menu;

    private WeekDay currentDay;

    public MenuSerializer(Menu menu) {
        this.menu = menu;
    }

    public String serialize() {
        return "{\"days\": [" + menu.days().stream().collect(StringBuilder::new, this::serializeDay, StringBuilder::append) + "]}";
    }

    private void serializeDay(StringBuilder stringBuilder, WeekDay day) {
        currentDay = day;
        stringBuilder.append("{")
                .append("\"name\": \"").append(day.getName()).append("\",")
                .append("\"recipes\": [")
                .append(day.recipes().stream().collect(StringBuilder::new, this::serializeRecipe, StringBuilder::append))
                .append("]}")
                .append(menu.days().indexOf(day) == menu.days().size() - 1 ? "" : ",");
    }

    private void serializeRecipe(StringBuilder stringBuilder, WebRecipe webRecipe) {
        stringBuilder.append("{")
                .append("\"title\": \"").append(webRecipe.getTitle()).append("\",")
                .append("\"url\": \"").append(webRecipe.getURI()).append("\"")
                .append("}")
                .append(currentDay.recipes().indexOf(webRecipe) == currentDay.recipes().size() - 1 ? "" : ",");
    }
}
