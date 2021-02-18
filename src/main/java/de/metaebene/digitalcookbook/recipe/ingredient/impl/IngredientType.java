package de.metaebene.digitalcookbook.recipe.ingredient.impl;

public enum IngredientType {

    STUECK(" Stück"),
    LITER("l"),
    MILLILITER("ml"),
    GRAMM("g"),
    KILOGRAMM("kg"),
    ESSLOEFFEL(" Esslöffel"),
    TEELOEFFEL(" Teelöffel"),
    PRISE(" Prise"),
    PACKUNG(" Packung"),
    TROPFEN(" Tropen"),
    SPRITZER(" Spritzer"),
    MESSERSPITZE(" Messerspitze"),
    TASSE(" Tasse"),
    BUND(" Bund");

    private String name;

    IngredientType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
