package de.metaebene.digitalcookbook.recipe;

import java.util.ArrayList;

public class RecipeHandler {

    private final ArrayList<Recipe> recipeArrayList;

    public RecipeHandler() {
        this.recipeArrayList = new ArrayList<>();
    }

    public ArrayList<Recipe> getRecipesByType(RecipeType recipeType) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : this.recipeArrayList) {
            if (recipe.getRecipeType() == recipeType) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipeArrayList.add(recipe);
    }

    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }
}
