package de.metaebene.digitalcookbook.recipe.ingredient.impl;

public class Ingredient {

    private final String ingredientID;
    private final String ingredientName;
    private final IngredientType ingredientType;

    public Ingredient(String ingredientID, String ingredientName, IngredientType ingredientType) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
    }

    public String getIngredientID() {
        return ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }
}
