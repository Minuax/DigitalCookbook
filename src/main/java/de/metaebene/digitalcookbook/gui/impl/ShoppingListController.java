package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.Ingredient;
import de.metaebene.digitalcookbook.recipe.ingredient.impl.IngredientType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingListController implements Initializable {

    public TextField amountField;
    public ComboBox<String> ingredientSelection;
    public Button addIngredientButton;

    public TextField newIngredientField;
    public ComboBox<IngredientType> newIngredientTypeComboBox;
    public Button createNewIngredientButton;

    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> ingredientList = FXCollections.observableArrayList();

        for (Ingredient ingredient : DigitalCookbook.instance.getIngredientHandler().getIngredientArrayList()) {
            ingredientList.add(ingredient.getIngredientName());
        }

        ingredientSelection.setItems(ingredientList.sorted());

        // TODO: Finish adding components
        addIngredientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (amountField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Keine Mengenangabe eingegeben!", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }

                if (ingredientSelection.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Kein Zutatentyp ausgewählt!", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }

                if (!NumberUtils.isCreatable(amountField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Eingegebene Menge ist keine Fließkommazahl!", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }

            }
        });

        newIngredientTypeComboBox.setItems(FXCollections.observableArrayList(IngredientType.values()));

        // TODO: Creating new Ingredient
        createNewIngredientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        backButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().backToMainMenu());
    }
}
