package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.IngredientType;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class RecipeHandler {

    private ArrayList<Recipe> recipeArrayList;

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
