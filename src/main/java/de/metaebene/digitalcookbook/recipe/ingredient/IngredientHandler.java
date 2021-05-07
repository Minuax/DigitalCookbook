package de.metaebene.digitalcookbook.recipe.ingredient;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.IngredientType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class IngredientHandler {

    private final ArrayList<Ingredient> ingredientArrayList;

    /**
     * Konstruktor der Klasse IngredientHandler
     */
    public IngredientHandler() {
        this.ingredientArrayList = new ArrayList<>();
    }

    /**
     * Methode, welche für den gebenen Namen einer Zutat den richtigen Typ zurückgibt
     * @param name Gesuchter Name
     * @return Korrekt Ingredient-Typ
     */
    public IngredientType parseType(String name) {
        IngredientType ingredientType = null;
        for (IngredientType type : IngredientType.values()) {
            if (type.name().equalsIgnoreCase(name))
                ingredientType = type;
        }
        return ingredientType;
    }

    /**
     * Methode, welche für die gebene ID einer Zutat den richtigen Typ zurückgibt
     * @param ingredientID Gesuchte ID
     * @return Korrekt Ingredient-Typ
     */
    public Ingredient parseIngredient(int ingredientID) {
        Ingredient ingredient = null;

        for (Ingredient i : this.ingredientArrayList) {
            if (i.getIngredientID() == ingredientID) {
                ingredient = i;
            }
        }
        return ingredient;
    }

    /**
     * Prüft anhand einer Datenbank, wie viele Rezepte eingetragen sind
     * @return Anzahl der Rezepte in der Datenbank
     */
    public int getIngredientCount() {
        int ingredientCount = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/ingredientsize.php")).openStream()));
            String result = bufferedReader.readLine();

            bufferedReader.close();
            if (result != null && !result.equalsIgnoreCase("error"))
                ingredientCount = Integer.parseInt(result);
        } catch (IOException e) {
            System.out.println("we fucked up :C");
        }
        return ingredientCount;
    }

    /**
     * Gtter für die Liste aller Rezepte
     * @return Instanz von ingredientArrayList
     */
    public ArrayList<Ingredient> getIngredientArrayList() {
        return ingredientArrayList;
    }
}
