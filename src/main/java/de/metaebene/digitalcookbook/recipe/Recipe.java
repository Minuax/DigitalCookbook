package de.metaebene.digitalcookbook.recipe;

import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {

    private final int recipeID, recipePortions;
    private final String recipeTitle, recipeDescription, recipeWorktime, recipeCooktime;
    private final RecipeType recipeType;

    private Image recipeImage;

    private final ArrayList<Instruction> recipeInstructionArrayList;
    private final HashMap<Ingredient, Double> recipeIngredientHashmap;

    /**
     * Konstruktor der Klasse Recipe
     * @param recipeID Eindeutige ID des Rezeptes
     * @param recipePortions Anzahl der Portionen, welche mit angegebenen Mengen gekocht werden können
     * @param recipeTitle Titel des Rezeptes
     * @param recipeDescription Beschreibung des Rezeptes
     * @param recipeWorktime Zubereitungszeit
     * @param recipeCooktime Back/Kochzeit
     * @param recipeType Rezepttyp (Vor-, Haupt-, Nachspeise)
     */
    public Recipe(int recipeID, int recipePortions, String recipeTitle, String recipeDescription, String recipeWorktime, String recipeCooktime, RecipeType recipeType) {
        this.recipeID = recipeID;
        this.recipePortions = recipePortions;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeWorktime = recipeWorktime;
        this.recipeCooktime = recipeCooktime;
        this.recipeType = recipeType;

        this.recipeImage = new Image("http://fjg31.ddns.net/recipeimages/" + this.recipeID + ".jpg");

        if (this.recipeImage.getHeight() == 0) {
            this.recipeImage = new Image("http://fjg31.ddns.net/recipeimages/404.jpg");
        }

        this.recipeInstructionArrayList = new ArrayList<>();
        this.recipeIngredientHashmap = new HashMap<>();
    }

    /**
     * Getter für Rezept-ID
     * @return Instanz der recipeID
     */
    public int getRecipeID() {
        return recipeID;
    }

    /**
     * Getter für Rezept-Portionen
     * @return Instanz der recipePortions
     */
    public int getRecipePortions() {
        return recipePortions;
    }

    /**
     * Getter für Rezept-Titel
     * @return Instanz des recipeTitle
     */
    public String getRecipeTitle() {
        return recipeTitle;
    }

    /**
     * Getter für Rezept-Beschreibung
     * @return Instanz der recipeDescription
     */
    public String getRecipeDescription() {
        return recipeDescription;
    }

    /**
     * Getter für Zubereitungszeit
     * @return Instanz der recipeWorktime
     */
    public String getRecipeWorktime() {
        return recipeWorktime;
    }

    /**
     * Getter für Rezept-ID
     * @return Instanz der recipeID
     */
    public String getRecipeCooktime() {
        return recipeCooktime;
    }

    /**
     * Getter für Rezept-Bild
     * @return Instanz des recipeImage
     */
    public Image getRecipeImage() {
        return recipeImage;
    }

    /**
     * Getter für Anweisungsliste
     * @return Instanz der recipeInstructionArrayList
     */
    public ArrayList<Instruction> getRecipeInstructionArrayList() { return recipeInstructionArrayList; }

    /**
     * Getter für Zutatenliste
     * @return Instanz der recipeIngredientHashmap
     */
    public HashMap<Ingredient, Double> getRecipeIngredientHashmap() { return recipeIngredientHashmap; }

    /**
     * Getter für Rezepttyp
     * @return Instanz des rezeptType
     */
    public RecipeType getRecipeType() { return recipeType; }
}
