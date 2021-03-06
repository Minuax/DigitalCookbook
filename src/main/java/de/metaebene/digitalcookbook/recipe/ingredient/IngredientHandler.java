package de.metaebene.digitalcookbook.recipe.ingredient;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.IngredientType;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class IngredientHandler {

    private final ArrayList<Ingredient> ingredientArrayList;

    public IngredientHandler() {
        this.ingredientArrayList = new ArrayList<>();
    }

    public IngredientType parseType(String name) {
        IngredientType ingredientType = null;
        for (IngredientType type : IngredientType.values()) {
            if (type.name().equalsIgnoreCase(name))
                ingredientType = type;
        }
        return ingredientType;
    }

    public Ingredient parseIngredient(int ingredientID) {
        Ingredient ingredient = null;

        for (Ingredient i : this.ingredientArrayList) {
            if (i.getIngredientID() == ingredientID) {
                ingredient = i;
            }
        }
        return ingredient;
    }

    public Ingredient parseIngredient(String ingredientName) {
        Ingredient ingredient = null;
        for (Ingredient i : this.ingredientArrayList) {
            if (i.getIngredientName().equalsIgnoreCase(ingredientName)) {
                ingredient = i;
            }
        }
        return ingredient;
    }

    public Ingredient submitIngredient(String ingredientName, IngredientType ingredientType) {
        Ingredient ingredient = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/submitingredient.php?ingredientName=" + ingredientName + "&ingredientType=" + ingredientType)).openStream()));
            String result = bufferedReader.readLine();

            bufferedReader.close();

            ingredient = new Ingredient(Integer.parseInt(result), ingredientName, ingredientType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

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

    public ArrayList<Ingredient> getIngredientArrayList() {
        return ingredientArrayList;
    }
}
