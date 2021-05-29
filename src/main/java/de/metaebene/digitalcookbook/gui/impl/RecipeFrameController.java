package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeFrameController implements Initializable {

    public ImageView imageView;

    public Label titleLabel;
    public Label ingredientsLabel;
    public Label worktimeLabel;
    public Label cooktimeLabel;
    public Label portionLabel;

    public TextArea descriptionField;
    public TextArea ingredientField;
    public TextArea instructionField;

    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeImage());
        titleLabel.setText(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeTitle());
        worktimeLabel.setText("Arbeitszeit: " + DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeWorktime());
        cooktimeLabel.setText("Kochzeit: " + DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeCooktime());
        portionLabel.setText("Portionen: " + DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipePortions());

        descriptionField.setText(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeDescription());


        StringBuilder instructions = new StringBuilder();
        for (int i = 0; i < DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeInstructionArrayList().size(); i++) {
            instructions.append(i + 1).append(". ").append(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeInstructionArrayList().get(i).getInstructionDescription()).append("\n");
        }
        instructionField.setText(instructions.toString());

        StringBuilder ingredients = new StringBuilder();
        for (Ingredient ingredient : DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeIngredientHashmap().keySet()) {
            ingredients.append(ingredient.getIngredientName()).append(", ").append(Math.round(DigitalCookbook.instance.getFrameHandler().getCurrentRecipe().getRecipeIngredientHashmap().get(ingredient))).append(" ").append(ingredient.getIngredientType().toString()).append("\n");
        }
        ingredientField.setText(ingredients.toString());


        backButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openMainMenu());
    }
}
