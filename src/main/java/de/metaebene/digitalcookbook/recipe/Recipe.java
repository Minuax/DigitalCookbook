package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Recipe {

    private int recipeID, recipePortions;
    private String recipeTitle, recipeDescription, recipeWorktime, recipeCooktime;
    private RecipeType recipeType;

    private ArrayList<Instruction> recipeInstructionArrayList;
    private ArrayList<Ingredient> recipeIngredientArrayList;
    private ArrayList<Image> imageArrayList;

    public Recipe(int recipeID, int recipePortions, String recipeTitle, String recipeDescription, String recipeWorktime, String recipeCooktime, RecipeType recipeType) {
        this.recipeID = recipeID;
        this.recipePortions = recipePortions;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeWorktime = recipeWorktime;
        this.recipeCooktime = recipeCooktime;
        this.recipeType = recipeType;

        this.recipeInstructionArrayList = new ArrayList<>();
        this.recipeIngredientArrayList = new ArrayList<>();
        this.imageArrayList = new ArrayList<>();
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

    public ArrayList<Image> getImageArrayList() {
        return imageArrayList;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public void addImage(Image image) {
        this.imageArrayList.add(image);
    }
}
