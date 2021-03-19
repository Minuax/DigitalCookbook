package de.metaebene.digitalcookbook.file;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import de.metaebene.digitalcookbook.recipe.RecipeType;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    private final File recipeDir, ingredientFile;

    public FileHandler() {
        this.recipeDir = new File(DigitalCookbook.instance.getDataDir(), "recipes");
        this.ingredientFile = new File(DigitalCookbook.instance.getDataDir(), "ingredients.config");

        if (!this.recipeDir.isDirectory()) {
            if (this.recipeDir.mkdirs())
                System.out.println("Recipe directory created");
        }

        try {
            loadIngredients();
            loadRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        downloadIngredients();
        downloadRecipes();
    }

    public void downloadRecipes() {
        int recipeCount = DigitalCookbook.instance.getRecipeHandler().getRecipeCount();
        if (DigitalCookbook.instance.getRecipeHandler().getRecipeArrayList().size() != recipeCount) {
            for (File file : recipeDir.listFiles()) {
                file.delete();
            }

            System.out.println("Recipe count does not match, redownloading.");
            for (int i = 0; i < recipeCount; i++) {
                System.out.println("Now downloading Recipe with ID " + i);
                File recipeDir = new File(this.recipeDir, i + "");
                recipeDir.mkdirs();

                File recipeFile = new File(recipeDir, i + ".json");
                File recipeImage = new File(recipeDir, i + ".jpg");

                try {
                    FileUtils.copyURLToFile(new URL("http://fjg31.ddns.net/recipes/" + i + "/" + i + ".json"), recipeFile);
                    FileUtils.copyURLToFile(new URL("http://fjg31.ddns.net/recipes/" + i + "/" + i + ".jpg"), recipeImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Finished downloading recipes.");
        }

        try {
            loadRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadIngredients() {
        int ingredientCount = DigitalCookbook.instance.getIngredientHandler().getIngredientCount();
        if (DigitalCookbook.instance.getIngredientHandler().getIngredientArrayList().size() != ingredientCount) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new URL("http://fjg31.ddns.net/listingredients.php")).openStream()));
                String readLine = bufferedReader.readLine();

                for (String line : readLine.split("<br>")) {
                    DigitalCookbook.instance.getIngredientHandler().getIngredientArrayList().add(new Ingredient(Integer.parseInt(line.split(":")[0]), line.split(":")[1], DigitalCookbook.instance.getIngredientHandler().parseType(line.split(":")[2])));

                }

                bufferedReader.close();

                saveIngredients();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveRecipes() {
        for (Recipe recipe : DigitalCookbook.instance.getRecipeHandler().getRecipeArrayList()) {
            File directory = new File(recipeDir, recipe.getRecipeID() + "");
            if (directory.exists() || directory.isDirectory())
                if (directory.delete())
                    System.out.println("Found recipe directory, deleting.");

            if (directory.mkdirs())
                System.out.println("Creating new recipe directory");

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
        DigitalCookbook.instance.getRecipeHandler().getRecipeArrayList().clear();
        for (File file : this.recipeDir.listFiles()) {
            if (file.isDirectory()) {
                for (File recipeFile : file.listFiles()) {
                    if (recipeFile.getName().endsWith(".json")) {
                        FileInputStream fileInputStream = new FileInputStream(recipeFile);
                        String text = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

                        JSONObject jsonObject = new JSONObject(text);

                        Recipe recipe = new Recipe(jsonObject.getInt("recipeID"), jsonObject.getInt("recipePortions"), jsonObject.getString("recipeTitle"), jsonObject.getString("recipeDescription"), jsonObject.getString("recipeWorktime"), jsonObject.getString("recipeCooktime"), jsonObject.getEnum(RecipeType.class, "recipeType"));

                        JSONArray recipeInstructionJSONArray = jsonObject.getJSONArray("recipeInstructionArrayList");
                        for (int i = 0; i < recipeInstructionJSONArray.length(); i++) {
                            Instruction instruction = new Instruction(recipeInstructionJSONArray.getString(i));

                            recipe.getRecipeInstructionArrayList().add(instruction);
                        }

                        JSONArray recipeIngredientJSONArray = jsonObject.getJSONArray("recipeIngredientArrayList");
                        for (int i = 0; i < recipeIngredientJSONArray.length(); i++) {
                            Ingredient ingredient = DigitalCookbook.instance.getIngredientHandler().parseIngredient(Integer.parseInt(recipeIngredientJSONArray.getString(i).split(":")[0]));
                            if (ingredient != null)
                                recipe.getRecipeIngredientHashmap().put(ingredient, Double.parseDouble(recipeIngredientJSONArray.getString(i).split(":")[1]));
                        }

                        DigitalCookbook.instance.getRecipeHandler().addRecipe(recipe);
                    }
                }
            }
        }
    }

    /**
     * Method for saving modules
     */
    public void saveIngredients() {
        if (ingredientFile.exists()) {
            ingredientFile.delete();
        }
        try {
            ingredientFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ingredientFile));
            for (Ingredient ingredient : DigitalCookbook.instance.getIngredientHandler().getIngredientArrayList()) {
                writer.write(ingredient.getIngredientID() + ":" + ingredient.getIngredientName() + ":" + ingredient.getIngredientType().toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for loading modules
     */
    public void loadIngredients() {
        try {
            if (!ingredientFile.exists()) {
                ingredientFile.createNewFile();
            }
            BufferedReader read = new BufferedReader(new FileReader(ingredientFile));
            for (Object s : read.lines().toArray()) {
                String r = (String) s;
                Ingredient ingredient = new Ingredient(Integer.parseInt(r.split(":")[0]), r.split(":")[1], DigitalCookbook.instance.getIngredientHandler().parseType(r.split(":")[2]));
                DigitalCookbook.instance.getIngredientHandler().getIngredientArrayList().add(ingredient);
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRememberMe(String username, String password) {
        File loginFile = new File(DigitalCookbook.instance.getDataDir(), "credentials.config");
        if (loginFile.exists()) {
            loginFile.delete();
        }
        try {
            loginFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(loginFile));
            writer.write(username + ":" + password);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkRememberMe() {
        File loginFile = new File(DigitalCookbook.instance.getDataDir(), "credentials.config");
        try {
            if (!loginFile.exists()) {
                return false;
            }
            BufferedReader read = new BufferedReader(new FileReader(loginFile));
            for (Object s : read.lines().toArray()) {
                String r = (String) s;
                return DigitalCookbook.instance.getWebHandler().auth(r.split(":")[0], r.split(":")[1]);
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
