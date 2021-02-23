package de.metaebene.digitalcookbook.file;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import de.metaebene.digitalcookbook.recipe.RecipeType;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    private File recipeDir;

    public FileHandler() {
        this.recipeDir = new File(DigitalCookbook.instance.getDataDir(), "recipes");
        if (!this.recipeDir.isDirectory()) {
            this.recipeDir.mkdirs();
        }

        try {
            loadRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRecipes() {
        for (Recipe recipe : DigitalCookbook.instance.getRecipeHandler().getRecipeArrayList()) {
            File directory = new File(recipeDir, recipe.getRecipeID() + "");
            if (directory.exists() || directory.isDirectory())
                directory.delete();

            directory.mkdirs();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("recipeID", recipe.getRecipeID());
            jsonObject.put("recipePortions", recipe.getRecipePortions());
            jsonObject.put("recipeTitle", recipe.getRecipeTitle());
            jsonObject.put("recipeDescription", recipe.getRecipeDescription());
            jsonObject.put("recipeWorktime", recipe.getRecipeWorktime());
            jsonObject.put("recipeCooktime", recipe.getRecipeCooktime());
            jsonObject.put("recipeType", recipe.getRecipeType());

            JSONArray instructionArray = new JSONArray();
            for (Instruction instruction : recipe.getRecipeInstructionArrayList()) {
                instructionArray.put(instruction.getInstructionDescription());
            }

            JSONArray ingredientArray = new JSONArray();
            for (Ingredient ingredient : recipe.getRecipeIngredientHashmap().keySet()) {
                ingredientArray.put(ingredient.getIngredientID() + ":" + recipe.getRecipeIngredientHashmap().get(ingredient));
            }

            jsonObject.put("recipeInstructionArrayList", instructionArray);
            jsonObject.put("recipeIngredientArrayList", ingredientArray);

            try (FileWriter file = new FileWriter(new File(directory, recipe.getRecipeID() + ".json"))) {
                file.write(jsonObject.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadRecipes() throws IOException {
        for (File file : this.recipeDir.listFiles()) {
            if (file.isDirectory()) {
                for (File recipeFile : file.listFiles()) {
                    if (recipeFile.getName().endsWith(".json")) {
                        FileInputStream fileInputStream = new FileInputStream(recipeFile);
                        String text = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

                        JSONObject jsonObject = new JSONObject(text);

                        Recipe recipe = new Recipe(jsonObject.getString("recipeID"), jsonObject.getInt("recipePortions"), jsonObject.getString("recipeTitle"), jsonObject.getString("recipeDescription"), jsonObject.getString("recipeWorktime"), jsonObject.getString("recipeCooktime"), jsonObject.getEnum(RecipeType.class, "recipeType"));

                        JSONArray recipeInstructionJSONArray = jsonObject.getJSONArray("recipeInstructionArrayList");
                        for (int i = 0; i < recipeInstructionJSONArray.length(); i++) {
                            Instruction instruction = new Instruction(recipeInstructionJSONArray.getString(i));

                            recipe.getRecipeInstructionArrayList().add(instruction);
                        }

                        JSONArray recipeIngredientJSONArray = jsonObject.getJSONArray("recipeIngredientArrayList");
                        for (int i = 0; i < recipeIngredientJSONArray.length(); i++) {
                            Ingredient ingredient = DigitalCookbook.instance.getIngredientHandler().getIngredientByID(recipeIngredientJSONArray.getString(i).split(":")[0]);
                            recipe.getRecipeIngredientHashmap().put(ingredient, Double.parseDouble(recipeIngredientJSONArray.getString(i).split(":")[1]));
                        }

                        DigitalCookbook.instance.getRecipeHandler().addRecipe(recipe);
                    }
                }
            }
        }
    }

}
