package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.instruction.Instruction;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeFrameController implements Initializable {


    public ImageView imageView;

    public Label titleLabel;
    public Label ingredientsLabel;

    public TextArea descriptionField;
    public TextArea ingredientField;
    public TextArea instructionField;

    public Button backButton;
    public Button addToShoppingcartButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getImageArrayList().isEmpty() ? new Image("placeholder.png") : DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getImageArrayList().get(0));
        titleLabel.setText(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeTitle());

        descriptionField.setText(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeDescription());


        StringBuilder instructions = new StringBuilder();
        for (int i = 0; i < DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeInstructionArrayList().size(); i++) {
            instructions.append(i + 1).append(". ").append(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeInstructionArrayList().get(i).getInstructionDescription()).append("\n");
        }
        instructionField.setText(instructions.toString());

        StringBuilder ingredients = new StringBuilder();
        for (Ingredient ingredient : DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeIngredientArrayList()) {
            ingredients.append(ingredient.getIngredientName()).append(ingredient.getIngredientType().toString()).append("\n");
        }
        ingredientField.setText(ingredients.toString());


        backButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().backToMainMenu());
        addToShoppingcartButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().backToMainMenu());
    }
}
