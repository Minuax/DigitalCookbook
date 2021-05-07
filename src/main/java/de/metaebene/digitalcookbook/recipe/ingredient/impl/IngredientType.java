package de.metaebene.digitalcookbook.recipe.ingredient.impl;

public enum IngredientType {

    STUECK("Stück"),
    LITER("Liter"),
    MILLILITER("Milliliter"),
    GRAMM("Gramm"),
    KILOGRAMM("Kilogramm"),
    ESSLOEFFEL("Esslöffel"),
    TEELOEFFEL("Teelöffel"),
    PRISE("Prise"),
    PACKUNG("Packung"),
    TROPFEN("Tropen"),
    SPRITZER("Spritzer"),
    MESSERSPITZE("Messer"),
    TASSE("Tasse"),
    BUND("Bund");

    private String name;

    /**
     * Konstruktor des Enum IngredientType
     * @param name Übergibt den Namen des Enum
     */
    IngredientType(String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen des Ingredient-Type zurück
     * @return Name des Ingredient-Types
     */
    @Override
    public String toString() {
        return name;
    }
}
