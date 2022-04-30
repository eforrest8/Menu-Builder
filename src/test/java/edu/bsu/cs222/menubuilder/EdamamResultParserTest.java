package edu.bsu.cs222.menubuilder;

import edu.bsu.cs222.menubuilder.model.EdamamResultParser;
import edu.bsu.cs222.menubuilder.model.NutrientInfo;
import edu.bsu.cs222.menubuilder.model.WebRecipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class EdamamResultParserTest {
    private final EdamamResultParser parser = new EdamamResultParser();

    public static Stream<Arguments> testParseArgumentSource() {
        WebRecipe gravy = new WebRecipe("Tomato Gravy", "http://www.edamam.com/recipe/tomato-gravy-1155648f37e539dc36b847ddbf7f53f7/tomato");
        gravy.setTotalNutrients(Map.of(
                "ENERC_KCAL", new NutrientInfo("Energy", 1018.1076296134726, "kcal"),
                "VITA_RAE", new NutrientInfo("Vitamin A", 463.16877651846664, "µg")
        ));
        gravy.setTotalDaily(Map.of(
                "ENERC_KCAL", new NutrientInfo("Energy", 50.905381480673626, "%"),
                "VITA_RAE", new NutrientInfo("Vitamin A", 51.463197390940735, "%")
        ));
        WebRecipe gratedTomato = new WebRecipe("Grated Tomato", "http://www.edamam.com/recipe/grated-tomato-c7efb0a67ef7e4a406cbcc3868050090/tomato");
        gratedTomato.setTotalNutrients(Map.of(
                "ENERC_KCAL", new NutrientInfo("Energy", 92.87303775750001, "kcal"),
                "VITA_RAE", new NutrientInfo("Vitamin A", 216.70375476750002, "µg")
        ));
        gratedTomato.setTotalDaily(Map.of(
                "ENERC_KCAL", new NutrientInfo("Energy", 4.643651887875001, "%"),
                "VITA_RAE", new NutrientInfo("Vitamin A", 24.07819497416667, "%")
        ));
        List<WebRecipe> normalRecipes = List.of(gravy, gratedTomato);
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
