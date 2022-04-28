package edu.bsu.cs222.menubuilder.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * This class directly represents Edamam's NutrientInfo format.
 */
public record NutrientInfo(String label, double quantity, String unit) {
}
