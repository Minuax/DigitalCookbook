package de.metaebene.digitalcookbook.gui;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FrameHandler {

    private Recipe currentRecipe;

    /**
     * Öffnet den RecipeFrame für ein übergebenes Rezept
     * @param recipe zu-öffnendes Rezept
     */
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

    /**
     * Öffnet das Hauptmenü der Applikation
     */
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

    /**
     * Getter für das momentane Rezept
     * @return Instanz des momentanen Rezeptes
     */
    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }
}
