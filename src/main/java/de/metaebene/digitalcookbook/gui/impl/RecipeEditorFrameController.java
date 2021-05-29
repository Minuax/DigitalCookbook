package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.recipe.Recipe;
import de.metaebene.digitalcookbook.recipe.RecipeType;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeEditorFrameController implements Initializable {

    private boolean thumbnailChosen;

    public ImageView imageView;

    public ComboBox<Ingredient> ingredientSelection;
    public ComboBox<RecipeType> recipeTypeCombobox;

    public TextField titleField;
    public TextField workTimeField;
    public TextField portionField;
    public TextField cookTimeField;
    public TextField instructionTextField;
    public TextField amountField;

    public TextArea descriptionField;
    public TextArea ingredientField;
    public TextArea instructionField;

    public Button addIngredientButton;
    public Button addInstructionButton;
    public Button backButton;
    public Button saveRecipeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                thumbnailChosen = true;
            }
        });

        saveRecipeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (titleField.getText().isEmpty() || recipeTypeCombobox.getValue() == null || workTimeField.getText().isEmpty() || portionField.getText().isEmpty() || cookTimeField.getText().isEmpty() || descriptionField.getText().isEmpty() || ingredientField.getText().isEmpty() || instructionField.getText().isEmpty() || !thumbnailChosen)
                    return;

                Recipe recipe = new Recipe(Integer.parseInt(portionField.getText()), titleField.getText(), descriptionField.getText(), workTimeField.getText(), cookTimeField.getText(), recipeTypeCombobox.getValue(), imageView.getImage());
            }
        });
    }
}
