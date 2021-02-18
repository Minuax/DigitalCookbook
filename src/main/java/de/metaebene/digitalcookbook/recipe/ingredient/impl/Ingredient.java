package de.metaebene.digitalcookbook.recipe.ingredient.impl;

public class Ingredient {

    private int ingredientID;
    private String ingredientName;
    private IngredientType ingredientType;

    public Ingredient(int ingredientID, String ingredientName, IngredientType ingredientType) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }
}
