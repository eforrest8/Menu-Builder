package edu.bsu.cs222.menubuilder.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NutrientInfoTest {

    @Test
    void testSumQuantities() {
        NutrientInfo first = new NutrientInfo("label", 10, "unit");
        NutrientInfo second = new NutrientInfo("label", 25, "unit");
        NutrientInfo expected = new NutrientInfo("label", 35, "unit");
        Assertions.assertEquals(expected, first.sumQuantities(second));
    }
}