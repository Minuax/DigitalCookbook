package de.metaebene.digitalcookbook.gui;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FrameHandler {

    private Recipe currentRecipe;


    public FrameHandler() {

    }

    public void openRecipeFrame(Recipe recipe) {
        Parent p = null;
        this.currentRecipe = recipe;

        try {
            p = FXMLLoader.load(getClass().getResource("/Recipe.fxml"));
            Scene scene = new Scene(p);


            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToMainMenu() {
        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource("/Main.fxml"));
            Scene scene = new Scene(p);

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
