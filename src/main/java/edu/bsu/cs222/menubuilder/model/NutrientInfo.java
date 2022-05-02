package edu.bsu.cs222.menubuilder.model;

/**
 * This class directly represents Edamam's NutrientInfo format.
 */
public record NutrientInfo(String label, double quantity, String unit) {
    public NutrientInfo sumQuantities(NutrientInfo second) {
        return new NutrientInfo(label(), quantity() + second.quantity(), unit());
    }
}
