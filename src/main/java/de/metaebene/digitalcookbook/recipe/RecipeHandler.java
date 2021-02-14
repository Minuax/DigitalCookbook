package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.IngredientType;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class RecipeHandler {

    private ArrayList<Recipe> recipeArrayList;

    public RecipeHandler() {
        this.recipeArrayList = new ArrayList<>();

        this.recipeArrayList.add(new Recipe(1, 4, "ChopSuey", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea", "8 Jahre", "5", RecipeType.VORSPEISE));
        this.recipeArrayList.add(new Recipe(2, 4, "Huhn", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea", "8 Jahre", "5", RecipeType.HAUPTSPEISE));
        this.recipeArrayList.add(new Recipe(3, 4, "Judendreck", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea", "8 Jahre", "5", RecipeType.HAUPTSPEISE));
        this.recipeArrayList.add(new Recipe(4, 4, "Zyklon B", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea", "8 Jahre", "5", RecipeType.NACHSPEISE));
        this.recipeArrayList.add(new Recipe(5, 4, "Deutschland", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea", "8 Jahre", "5", RecipeType.NACHSPEISE));

        for (Recipe recipe : this.recipeArrayList)
            for (int i = 0; i < 10; i++) {
                recipe.getRecipeInstructionArrayList().add(new Instruction(new Random().nextInt(888) + "asdasd"));
                recipe.getRecipeIngredientArrayList().add(new Ingredient(00001, new Random().nextInt(888) + "asdasdasd", IngredientType.values()[new Random().nextInt(IngredientType.values().length)]));
            }
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

    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }
}
