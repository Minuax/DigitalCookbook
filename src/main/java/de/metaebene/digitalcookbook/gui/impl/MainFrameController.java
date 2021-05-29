package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import de.metaebene.digitalcookbook.recipe.RecipeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    public ListView<Recipe> recipes;
    public TextField searchField;
    public ComboBox<RecipeType> mealSelection;
    public Button settingsButton;
    public Button createRecipeButton;

    private ObservableList<Recipe> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.observableList = FXCollections.observableArrayList(DigitalCookbook.instance.getRecipeHandler().getRecipesByType(RecipeType.HAUPTSPEISE));
        recipes.setItems(observableList);

        mealSelection.setItems(FXCollections.observableArrayList(RecipeType.values()));
        mealSelection.setValue(RecipeType.HAUPTSPEISE);
        mealSelection.setOnAction(event -> {
            observableList = FXCollections.observableArrayList(DigitalCookbook.instance.getRecipeHandler().getRecipesByType(mealSelection.getValue()));
            recipes.setItems(observableList);
            recipes.refresh();
        });

        recipes.setMaxWidth(460);
        recipes.setMaxHeight(280);

        recipes.setCellFactory(param -> new ListCell<Recipe>() {
            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                if (getItem() != null) {
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(recipe.getRecipeImage());
                        imageView.setFitWidth(222);
                        imageView.setFitHeight(130);

                        setText(recipe.getRecipeTitle() + "\n" + recipe.getRecipeDescription() + "\n\nPortionen: " + recipe.getRecipePortions() + "\nZubereitungszeit: " + recipe.getRecipeWorktime() + "\nGarzeit: " + recipe.getRecipeCooktime());
                        setWrapText(true);

                        setMinWidth(param.getWidth() - 20);
                        setMaxWidth(param.getWidth() - 20);
                        setPrefWidth(param.getWidth() - 20);

                        setGraphic(imageView);
                    }
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });

        recipes.setOnMouseClicked(event -> {
            if (recipes.getSelectionModel().getSelectedItem() != null)
                DigitalCookbook.instance.getFrameHandler().openRecipeFrame(recipes.getSelectionModel().getSelectedItem());
        });

        searchField.setPromptText("Suchbegriff eingeben..");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Recipe> recipeArrayList = DigitalCookbook.instance.getRecipeHandler().getRecipesByType(mealSelection.getValue());

            recipeArrayList.removeIf(recipe -> !recipe.getRecipeTitle().toLowerCase().contains(searchField.getText().toLowerCase()));

            observableList = FXCollections.observableArrayList(recipeArrayList);

            recipes.setItems(observableList);
            recipes.refresh();
        });

        createRecipeButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openFrame("RecipeEditor"));
        settingsButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openFrame("Settings"));
    }
}
