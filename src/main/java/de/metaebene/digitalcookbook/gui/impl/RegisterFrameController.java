package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RegisterFrameController implements Initializable {

    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField passwordField1;

    public Button backButton;
    public Button registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButton.setOnAction(event -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || passwordField1.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte fülle alle Felder aus!", ButtonType.OK);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            } else {
                if (passwordField.getText().equals(passwordField1.getText())) {
                    try {
                        String result = IOUtils.toString(DigitalCookbook.instance.getWebHandler().sendHTTPRequest("http://fjg31.ddns.net/register.php", Arrays.asList(new BasicNameValuePair("username", usernameField.getText()), new BasicNameValuePair("password", passwordField.getText()))), StandardCharsets.UTF_8.name());

                        if (result.equalsIgnoreCase("alreadyused")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Dieser Benuter existiert bereits!", ButtonType.OK);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.OK) {
                                usernameField.clear();
                                passwordField.clear();
                                passwordField1.clear();
                                alert.close();
                            }
                        } else if (NumberUtils.isCreatable(result)) {
                            DigitalCookbook.instance.setData(usernameField.getText(), Integer.parseInt(result));

                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Erfolgreich registriert!", ButtonType.OK);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.OK) {
                                DigitalCookbook.instance.getFrameHandler().openMainMenu();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehlern bei der Kommunikation mit dem Server, schließe Programm!", ButtonType.OK);
                            alert.showAndWait();

                            if (alert.getResult() == ButtonType.OK) {
                                System.exit(1);
                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Die Passwörter stimmen nicht überein!", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        passwordField.clear();
                        passwordField1.clear();
                        alert.close();
                    }
                }
            }
        });
        backButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openFrame("Login"));
    }
}
