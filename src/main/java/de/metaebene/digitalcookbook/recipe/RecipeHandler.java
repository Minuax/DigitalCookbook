package de.metaebene.digitalcookbook.recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RecipeHandler {

    private final ArrayList<Recipe> recipeArrayList;

    /**
     * Konstruktor der Klasse RecipeHandler
     */
    public RecipeHandler() {
        this.recipeArrayList = new ArrayList<>();
    }

    /**
     * Listet alle Rezepte eines gebenen Types auf
     * @param recipeType gesuchter Rezepttyp
     * @return eine Liste von Rezepten
     */
    public ArrayList<Recipe> getRecipesByType(RecipeType recipeType) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : this.recipeArrayList) {
            if (recipe.getRecipeType() == recipeType) {
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    /**
     * Liest die Gesamtzahl von in der Datenbank angelegten Rezepten aus
     * @return Gesamtzahl aller angelegten Rezepten
     */
    public int getRecipeCount() {
        int recipeCount = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/recipesize.php")).openStream()));
            String result = bufferedReader.readLine();

            bufferedReader.close();
            if (result != null && !result.equalsIgnoreCase("error"))
                recipeCount = Integer.parseInt(result);
        } catch (IOException e) {
            System.out.println("we fucked up :C");
        }
        return recipeCount;
    }

    /**
     * Fügt ein neues Rezept hinzu
     * @param recipe hinzuzufügendes Rezept
     */
    public void addRecipe(Recipe recipe) {
        this.recipeArrayList.add(recipe);
    }

    /**
     * Gibt die Rezeptliste zurück
     * @return Instanz von recipeArrayList
     */
    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }
}
