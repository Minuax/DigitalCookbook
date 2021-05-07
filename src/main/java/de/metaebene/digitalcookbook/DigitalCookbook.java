package de.metaebene.digitalcookbook;

import de.metaebene.digitalcookbook.file.FileHandler;
import de.metaebene.digitalcookbook.gui.FrameHandler;
import de.metaebene.digitalcookbook.recipe.RecipeHandler;
import de.metaebene.digitalcookbook.recipe.ingredient.IngredientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

/**
 * Die Klasse "DigitalCookbook" ist die Hauptklasse der Applikation.
 *
 * @author Michl, Reilly
 * @version 1.0.3
 */
public class DigitalCookbook extends Application {

    public static DigitalCookbook instance;

    private File dataDir;

    private FrameHandler frameHandler;
    private IngredientHandler ingredientHandler;
    private RecipeHandler recipeHandler;
    private FileHandler fileHandler;

    private Stage stage;

    /**
     * Main-Methode des Programmes
     *
     * @param args Startargumente
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Die Start-Methode des GUI, überschreibt die Methode "start()" aus Application.java
     *
     * @param primaryStage übergeben Stage
     */
    @Override
    public void start(Stage primaryStage) {
        instance = this; // Instanz der Hauptklasse ertellen und in globaler Variable speichern
        this.stage = primaryStage; // Stage-Variable setzen

        this.dataDir = new File(System.getProperty("user.home"), "DigitalCookBook"); // Datei-Directory definieren
        if (!this.dataDir.isDirectory()) {
            if (this.dataDir.mkdirs())
                System.out.println("Working directory erstellt!");
        }

        // Instanzen von benötigten Handlern erstellen
        this.frameHandler = new FrameHandler();
        this.ingredientHandler = new IngredientHandler();
        this.recipeHandler = new RecipeHandler();
        this.fileHandler = new FileHandler();

        try {
            // GUI-Dateien laden und anzeigen
            Parent p = FXMLLoader.load(getClass().getResource("/Main.fxml"));
            Scene scene = new Scene(p);

            primaryStage.setTitle("Digital Cookbook");
            primaryStage.getIcons().add(new Image("icons/icon.png"));

            scene.getStylesheets().add("style.css");

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> fileHandler.saveRecipes())); // Programm-Schließung -> Rezepte speichern
    }

    /**
     * Getter für Stage
     * @return gibt die Stage-Instanz zurück
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Getter für DataDir
     * @return gibt die DataDir-Instanz zurück
     */
    public File getDataDir() {
        return dataDir;
    }

    /**
     * Getter für FrameHandler
     * @return gibt die FrameHandler-Instanz zurück
     */
    public FrameHandler getFrameHandler() {
        return frameHandler;
    }

    /**
     * Getter für IngredientHandler
     * @return gibt die IngredientHandler-Instanz zurück
     */
    public IngredientHandler getIngredientHandler() {
        return ingredientHandler;
    }

    /**
     * Getter für RecipeHandler
     * @return gibt die RecipeHandler-Instanz zurück
     */
    public RecipeHandler getRecipeHandler() {
        return recipeHandler;
    }

    /**
     * Getter für FileHandler
     * @return gibt die FileHandler-Instanz zurück
     */
    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
