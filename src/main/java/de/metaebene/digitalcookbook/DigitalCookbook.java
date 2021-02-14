package de.metaebene.digitalcookbook;

import de.metaebene.digitalcookbook.gui.FrameHandler;
import de.metaebene.digitalcookbook.recipe.RecipeHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DigitalCookbook extends Application {

    public static DigitalCookbook instance;

    private FrameHandler frameHandler;
    private RecipeHandler recipeHandler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;

        this.frameHandler = new FrameHandler();
        this.recipeHandler = new RecipeHandler();

        try {
            Parent p = FXMLLoader.load(getClass().getResource("/Main.fxml"));
            Scene scene = new Scene(p);

            primaryStage.setTitle("Digital Cookbook");
            primaryStage.getIcons().add(new Image("icon.png"));

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FrameHandler getFrameHandler() {
        return frameHandler;
    }

    public RecipeHandler getRecipeHandler() {
        return recipeHandler;
    }
}
