package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.Recipe;
import de.metaebene.digitalcookbook.recipe.RecipeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {

    @FXML
    public ListView<Recipe> recipes;

    @FXML
    public TextField searchField;

    @FXML
    public ChoiceBox<RecipeType> mealSelection;

    private ObservableList observableList;

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
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                if (getItem() != null) {
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(recipe.getImageArrayList().isEmpty() ? new Image("placeholder.png") : recipe.getImageArrayList().get(0));
                        imageView.setFitWidth(222);
                        imageView.setFitHeight(130);

                        setText(recipe.getRecipeTitle() + "\n" + recipe.getRecipeDescription());
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


        //Set the filter Predicate whenever the filter changes.
        searchField.setPromptText("Suchbegriff eingeben..");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<Recipe> recipeArrayList = DigitalCookbook.instance.getRecipeHandler().getRecipesByType(mealSelection.getValue());

            recipeArrayList.removeIf(recipe -> !recipe.getRecipeTitle().toLowerCase().contains(searchField.getText().toLowerCase()));

            observableList = FXCollections.observableArrayList(recipeArrayList);

            //put the sorted list into the listview
            recipes.setItems(observableList);
            recipes.refresh();
        });

    }

}
