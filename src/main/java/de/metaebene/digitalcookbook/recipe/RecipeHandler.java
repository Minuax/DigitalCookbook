package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

    public int getRecipeCount() {
        int recipeCount = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/ingredientsize.php")).openStream()));
            String result = bufferedReader.readLine();

            bufferedReader.close();
            if (result != null && !result.equalsIgnoreCase("error"))
                recipeCount = Integer.parseInt(result);
        } catch (IOException e) {
            System.out.println("we fucked up :C");
        }
        return recipeCount;
    }

    public void addRecipe(Recipe recipe) {
        this.recipeArrayList.add(recipe);
    }

    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }
}
