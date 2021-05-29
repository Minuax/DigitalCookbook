package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFrameController implements Initializable {

    public TextField usernameField;
    public PasswordField passwordField;

    public Button loginButton;
    public Button registerButton;
    public CheckBox remembermeBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(event -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte fülle beide Felder aus!", ButtonType.OK);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            } else {
                DigitalCookbook.instance.getWebHandler().auth(usernameField.getText(), passwordField.getText());

                if (remembermeBox.isSelected())
                    DigitalCookbook.instance.getFileHandler().saveRememberMe(usernameField.getText(), passwordField.getText());
            }
        });

        registerButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openFrame("Register"));
    }
}
