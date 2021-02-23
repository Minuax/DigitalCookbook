package de.metaebene.digitalcookbook.recipe.ingredient.impl;

public class Ingredient {

    private String ingredientID;
    private String ingredientName;
    private IngredientType ingredientType;

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
