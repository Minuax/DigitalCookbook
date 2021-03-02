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

    public IngredientHandler() {
        this.ingredientArrayList = new ArrayList<>();

        downloadIngredients();
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
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/parseingredient.php?id=" + ingredientID)).openStream()));
            String result = bufferedReader.readLine();

            bufferedReader.close();
            if (result != null && !result.equalsIgnoreCase("notfound"))
                ingredient = new Ingredient(ingredientID, result.split(":")[1], parseType(result.split(":")[2]));
        } catch (IOException e) {
            System.out.println("we fucked up :C");
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


    public void downloadIngredients() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/listingredients.php")).openStream()));

            String readLine = bufferedReader.readLine();

            for (String line : readLine.split("<br>")) {
                this.ingredientArrayList.add(new Ingredient(Integer.parseInt(line.split(":")[0]), line.split(":")[1], parseType(line.split(":")[2])));
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ingredient> getIngredientArrayList() {
        return ingredientArrayList;
    }
}
