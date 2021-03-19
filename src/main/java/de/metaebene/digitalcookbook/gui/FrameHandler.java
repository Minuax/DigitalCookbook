package de.metaebene.digitalcookbook.gui;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FrameHandler {

    private Recipe currentRecipe;

    public void openRecipeFrame(Recipe recipe) {
        Parent p;
        this.currentRecipe = recipe;

        try {
            p = FXMLLoader.load(getClass().getResource("/Recipe.fxml"));
            Scene scene = new Scene(p);

            scene.getStylesheets().add("style.css");

            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openShoppingListFrame() {
        Parent p;

        try {
            p = FXMLLoader.load(getClass().getResource("/ShoppingList.fxml"));
            Scene scene = new Scene(p);

            scene.getStylesheets().add("style.css");

            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLoginFrame() {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Scene scene = new Scene(p);

            scene.getStylesheets().add("style.css");

            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRegisterFrame() {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/Register.fxml"));
            Scene scene = new Scene(p);

            scene.getStylesheets().add("style.css");

            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMainMenu() {
        Parent p;
        try {
            p = FXMLLoader.load(getClass().getResource("/Main.fxml"));
            Scene scene = new Scene(p);

            scene.getStylesheets().add("style.css");

            this.currentRecipe = null;

            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }
}
