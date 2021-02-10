package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;

import java.util.ArrayList;

public class Recipe {

    private int recipeID, recipePortions;
    private String recipeTitle, recipeDescription, recipeWorktime, recipeCooktime;

    private ArrayList<Instruction> recipeInstructionArrayList;
    private ArrayList<Ingredient> recipeIngredientArrayList;

    public Recipe(int recipeID, int recipePortions, String recipeTitle, String recipeDescription, String recipeWorktime, String recipeCooktime) {
        this.recipeID = recipeID;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeWorktime = recipeWorktime;
        this.recipeCooktime = recipeCooktime;

        this.recipeInstructionArrayList = new ArrayList<Instruction>();
        this.recipeIngredientArrayList = new ArrayList<Ingredient>();
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public String getRecipeWorktime() {
        return recipeWorktime;
    }

    public String getRecipeCooktime() {
        return recipeCooktime;
    }

    public ArrayList<Instruction> getRecipeInstructionArrayList() {
        return recipeInstructionArrayList;
    }

    public ArrayList<Ingredient> getRecipeIngredientArrayList() {
        return recipeIngredientArrayList;
    }
}
