package edu.bsu.cs222.menubuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MenuTest {

    private final WebRecipe firstRecipe = new WebRecipe("first", "https://example.com");
    private final WebRecipe secondRecipe = new WebRecipe("second", "https://example.com");
    private final WebRecipe thirdRecipe = new WebRecipe("third", "https://example.com");

    private final List<WebRecipe> initialRecipeList = List.of(
            firstRecipe,
            secondRecipe,
            thirdRecipe);

    private final Menu shiftFirstUpMenu = new Menu(DayOfWeek.MONDAY, initialRecipeList);

    private final Menu shiftSecondUpMenu = new Menu(DayOfWeek.MONDAY, List.of(
            secondRecipe,
            firstRecipe,
            thirdRecipe));

    private final Menu shiftSecondDownMenu = new Menu(DayOfWeek.MONDAY, List.of(
            firstRecipe,
            thirdRecipe,
            secondRecipe));

    private final Menu shiftThirdDownMenu = new Menu(DayOfWeek.MONDAY, initialRecipeList);

    private final Map<Consumer<Menu>, Menu> shiftUpTestCases = Map.of(
            menu -> menu.shiftRecipeUp(0), shiftFirstUpMenu,
            menu -> menu.shiftRecipeUp(1), shiftSecondUpMenu,
            menu -> menu.shiftRecipeUp(firstRecipe), shiftFirstUpMenu,
            menu -> menu.shiftRecipeUp(secondRecipe), shiftSecondUpMenu
    );

    private final Map<Consumer<Menu>, Menu> shiftDownTestCases = Map.of(
            menu -> menu.shiftRecipeDown(1), shiftSecondDownMenu,
            menu -> menu.shiftRecipeDown(2), shiftThirdDownMenu,
            menu -> menu.shiftRecipeDown(secondRecipe), shiftSecondDownMenu,
            menu -> menu.shiftRecipeDown(thirdRecipe), shiftThirdDownMenu
    );

    private Stream<Arguments> getShiftUpCaseStream() {
        return convertMapToCaseStream(shiftUpTestCases);
    }

    private Stream<Arguments> getShiftDownCaseStream() {
        return convertMapToCaseStream(shiftDownTestCases);
    }

    private Stream<Arguments> convertMapToCaseStream(Map<Consumer<Menu>, Menu> map) {
        return map.entrySet().stream()
                .map(entry -> Arguments.arguments(entry.getKey(), entry.getValue()));
    }

    @Test
    public void testAddRecipe() {
        Menu actual = new Menu(DayOfWeek.MONDAY);
        actual.addRecipe(firstRecipe);
        Menu expected = new Menu(DayOfWeek.MONDAY, List.of(firstRecipe));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveRecipe() {
        Menu actual = new Menu(DayOfWeek.MONDAY, new LinkedList<>(List.of(firstRecipe)));
        actual.removeRecipe(firstRecipe);
        Menu expected = new Menu(DayOfWeek.MONDAY);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveRecipeFromEmptyMenu() {
        Menu actual = new Menu(DayOfWeek.MONDAY);
        actual.removeRecipe(firstRecipe);
        Menu expected = new Menu(DayOfWeek.MONDAY);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getShiftUpCaseStream")
    public void testShiftRecipeUp(Consumer<Menu> shift, Menu expected) {
        Menu actual = new Menu(DayOfWeek.MONDAY, initialRecipeList);
        shift.accept(actual);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getShiftDownCaseStream")
    public void testShiftRecipeDown(Consumer<Menu> shift, Menu expected) {
        Menu actual = new Menu(DayOfWeek.MONDAY, initialRecipeList);
        shift.accept(actual);
        Assertions.assertEquals(expected, actual);
    }

}