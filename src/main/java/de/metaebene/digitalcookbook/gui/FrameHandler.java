package de.metaebene.digitalcookbook.gui;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FrameHandler {

    private Recipe currentRecipe;

    private boolean darkMode;

    public void openRecipeFrame(Recipe recipe) {
        this.currentRecipe = recipe;

        openFrame("Recipe");
    }

    public void openMainMenu() {
        this.currentRecipe = null;

        openFrame("Main");
    }

    public void openFrame(String fileName) {
        Parent p;
        try {
            p = FXMLLoader.load(getClass().getResource("/" + fileName + ".fxml"));
            Scene scene = new Scene(p);

            scene.getStylesheets().add(darkMode ? "style.css" : "style_light.css");

            this.currentRecipe = null;

            DigitalCookbook.instance.getStage().setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDarkMode() { return darkMode; }

    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }

    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }
}
