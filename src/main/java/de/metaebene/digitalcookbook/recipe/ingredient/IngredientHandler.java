package de.metaebene.digitalcookbook.recipe.ingredient;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.IngredientType;

import java.util.ArrayList;

public class IngredientHandler {

    private ArrayList<Ingredient> ingredientArrayList;

    public IngredientHandler() {
        this.ingredientArrayList = new ArrayList<>();


    }

    public Ingredient getIngredientByID(String id) {
        Ingredient ingredient = null;
        for (Ingredient ingredient1 : this.ingredientArrayList) {
            if (ingredient1.getIngredientID() == id) {
                ingredient = ingredient1;
            }
        }

        return new Ingredient("00000001", "test", IngredientType.KILOGRAMM);
    }

    public IngredientType parseType(String name) {
        IngredientType ingredientType = null;
        for (IngredientType type : IngredientType.values()) {
            if (type.name().equalsIgnoreCase(name))
                ingredientType = type;
        }
        return ingredientType;
    }

}
