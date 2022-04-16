package edu.bsu.cs222.menubuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MenuTest {

    private final WebRecipe firstRecipe = new WebRecipe("first", "https://example.com");
    private final WebRecipe secondRecipe = new WebRecipe("second", "https://example.com");
    private final WebRecipe thirdRecipe = new WebRecipe("third", "https://example.com");

    private final LinkedList<WebRecipe> initialRecipeList = new LinkedList<>(List.of(
            firstRecipe,
            secondRecipe,
            thirdRecipe));

    private final Menu initialShiftMenu = new Menu(DayOfWeek.MONDAY, initialRecipeList);

    private final Menu zeroShiftUpMenu = new Menu(DayOfWeek.MONDAY, initialRecipeList);

    private final Menu oneShiftUpMenu = new Menu(DayOfWeek.MONDAY, new LinkedList<>(List.of(
            secondRecipe,
            firstRecipe,
            thirdRecipe)));

    private final Map<Runnable, Menu> shiftTestCases = Map.of(
            () -> initialShiftMenu.shiftRecipeUp(0), zeroShiftUpMenu,
            () -> initialShiftMenu.shiftRecipeUp(1), oneShiftUpMenu,
            () -> initialShiftMenu.shiftRecipeUp(firstRecipe), zeroShiftUpMenu,
            () -> initialShiftMenu.shiftRecipeUp(secondRecipe), oneShiftUpMenu
    );

    private Stream<Arguments> getShiftCaseStream() {
        return shiftTestCases.entrySet().stream()
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
    @MethodSource("getShiftCaseStream")
    public void testShiftRecipeUp(Runnable shift, Menu expected) {
        shift.run();
        Assertions.assertEquals(expected, initialShiftMenu);
    }
}