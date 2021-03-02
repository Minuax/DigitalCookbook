package de.metaebene.digitalcookbook.shoppinglist;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;

import java.util.HashMap;

public class ShoppingListHandler {

    private HashMap<Ingredient, Double> shoppingList;

    public ShoppingListHandler() {
        this.shoppingList = new HashMap<>();
    }


}
