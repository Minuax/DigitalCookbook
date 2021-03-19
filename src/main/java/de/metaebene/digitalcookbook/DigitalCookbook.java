package de.metaebene.digitalcookbook;

import de.metaebene.digitalcookbook.file.FileHandler;
import de.metaebene.digitalcookbook.gui.FrameHandler;
import de.metaebene.digitalcookbook.recipe.RecipeHandler;
import de.metaebene.digitalcookbook.recipe.ingredient.IngredientHandler;
import de.metaebene.digitalcookbook.web.WebHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class DigitalCookbook extends Application {

    public static DigitalCookbook instance;

    private File dataDir;

    private FrameHandler frameHandler;
    private IngredientHandler ingredientHandler;
    private RecipeHandler recipeHandler;
    private FileHandler fileHandler;
    private WebHandler webHandler;

    private Stage stage;

    private String username;
    private int userID;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        this.stage = primaryStage;

        this.dataDir = new File(System.getProperty("user.home"), "DigitalCookBook");
        if (!this.dataDir.isDirectory()) {
            if (this.dataDir.mkdirs())
                System.out.println("Working directory erstellt!");
        }

        this.frameHandler = new FrameHandler();
        this.ingredientHandler = new IngredientHandler();
        this.recipeHandler = new RecipeHandler();
        this.fileHandler = new FileHandler();
        this.webHandler = new WebHandler();

        try {
            Parent p = FXMLLoader.load(getClass().getResource(fileHandler.checkRememberMe() ? "/Main.fxml" : "/Login.fxml"));
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

        Runtime.getRuntime().addShutdownHook(new Thread(() -> fileHandler.saveRecipes()));
    }

    public void setData(String username, int userID) {
        this.username = username;
        this.userID = userID;
    }

    public Stage getStage() {
        return stage;
    }

    public File getDataDir() {
        return dataDir;
    }

    public FrameHandler getFrameHandler() {
        return frameHandler;
    }

    public IngredientHandler getIngredientHandler() {
        return ingredientHandler;
    }

    public RecipeHandler getRecipeHandler() {
        return recipeHandler;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public WebHandler getWebHandler() {
        return webHandler;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }
}
