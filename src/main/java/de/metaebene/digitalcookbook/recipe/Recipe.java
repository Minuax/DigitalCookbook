package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {

    private final int recipeID, recipePortions;
    private final String recipeTitle, recipeDescription, recipeWorktime, recipeCooktime;
    private final RecipeType recipeType;

    private Image recipeImage;

    private final ArrayList<Instruction> recipeInstructionArrayList;
    private final HashMap<Ingredient, Double> recipeIngredientHashmap;

    public Recipe(int recipeID, int recipePortions, String recipeTitle, String recipeDescription, String recipeWorktime, String recipeCooktime, RecipeType recipeType) {
        this.recipeID = recipeID;
        this.recipePortions = recipePortions;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeWorktime = recipeWorktime;
        this.recipeCooktime = recipeCooktime;
        this.recipeType = recipeType;

        this.recipeImage = new Image("http://fjg31.ddns.net/recipeimages/" + this.recipeID + ".jpg");

        if (this.recipeImage.getHeight() == 0) {
            this.recipeImage = new Image("http://fjg31.ddns.net/recipeimages/404.jpg");
        }

        this.recipeInstructionArrayList = new ArrayList<>();
        this.recipeIngredientHashmap = new HashMap<>();
    }

    public int getRecipeID() {
        return recipeID;
    }

    public int getRecipePortions() {
        return recipePortions;
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

    public Image getRecipeImage() {
        return recipeImage;
    }

    public ArrayList<Instruction> getRecipeInstructionArrayList() { return recipeInstructionArrayList; }

    public HashMap<Ingredient, Double> getRecipeIngredientHashmap() { return recipeIngredientHashmap; }

    public RecipeType getRecipeType() { return recipeType; }
}
