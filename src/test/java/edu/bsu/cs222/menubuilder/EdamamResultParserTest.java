package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.EdamamResultParser;
import edu.bsu.cs222.menubuilder.model.EdamamSearchProvider;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public class EdamamResultParserTest {
    private final EdamamResultParser parser = new EdamamResultParser();

    public static Stream<Arguments> testParseArgumentSource() {
        List<WebRecipe> normalRecipes = List.of();
        return Stream.of(
                Arguments.arguments(
                        EdamamResultParserTest.class.getClassLoader().getResourceAsStream("Edamam Search Result Normal.json"),
                        normalRecipes
                ),
                Arguments.arguments(
                        EdamamResultParserTest.class.getClassLoader().getResourceAsStream("Edamam Search Result Empty.json"),
                        List.of()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testParseArgumentSource")
    public void testParse(InputStream stream, List<WebRecipe> expected) {
        List<WebRecipe> actual = parser.parse(stream);
        Assertions.assertEquals(expected, actual);
    }
}
