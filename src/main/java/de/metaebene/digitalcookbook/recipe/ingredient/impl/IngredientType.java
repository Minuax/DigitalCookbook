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

    IngredientType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
