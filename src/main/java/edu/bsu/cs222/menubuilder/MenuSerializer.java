package edu.bsu.cs222.menubuilder;

public class MenuSerializer {

    private final Menu menu;

    private Day currentDay;

    public MenuSerializer(Menu menu) {
        this.menu = menu;
    }

    public String serialize() {
        return "{\"days\": [" + menu.stream().collect(StringBuilder::new, this::serializeDay, StringBuilder::append) + "]}";
    }

    private void serializeDay(StringBuilder stringBuilder, Day day) {
        currentDay = day;
        stringBuilder.append("{")
                .append("\"name\": \"").append(day.getName()).append("\",")
                .append("\"recipes\": [")
                .append(day.stream().collect(StringBuilder::new, this::serializeRecipe, StringBuilder::append))
                .append("]}")
                .append(menu.indexOf(day) == menu.size() - 1 ? "" : ",");
    }

    private void serializeRecipe(StringBuilder stringBuilder, WebRecipe webRecipe) {
        stringBuilder.append("{")
                .append("\"title\": \"").append(webRecipe.getTitle()).append("\",")
                .append("\"url\": \"").append(webRecipe.getURI()).append("\"")
                .append("}")
                .append(currentDay.indexOf(webRecipe) == currentDay.size() - 1 ? "" : ",");
    }
}
