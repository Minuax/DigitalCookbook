package de.metaebene.digitalcookbook.recipe.ingredient.impl;

public class Ingredient {

    private final int ingredientID;
    private final String ingredientName;
    private final IngredientType ingredientType;

    /**
     * Konstructor der Klasse Ingredient
     * @param ingredientID die eindeutige Identifikationsnummer der Zutat
     * @param ingredientName der Klarname der Zutat
     * @param ingredientType der Typ, in welcher die Zutat angegeben wird (ml, gr, kg)
     */
    public Ingredient(int ingredientID, String ingredientName, IngredientType ingredientType) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
    }

    /**
     * Getter der Ingredient-ID
     * @return Instanz der ingredientID
     */
    public int getIngredientID() {
        return ingredientID;
    }

    /**
     * Getter des Ingredient-Name
     * @return Instanz des ingredientName
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Getter des IngredientType
     * @return Instanz des ingredientType
     */
    public IngredientType getIngredientType() {
        return ingredientType;
    }
}
