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

public class LoginFrameController implements Initializable {

    public TextField usernameField;
    public PasswordField passwordField;

    public Button loginButton;
    public Button registerButton;

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
                try {
                    String result = IOUtils.toString(DigitalCookbook.instance.getWebHandler().sendHTTPRequest("http://fjg31.ddns.net/login.php", Arrays.asList(new BasicNameValuePair("username", usernameField.getText()), new BasicNameValuePair("password", passwordField.getText()))), StandardCharsets.UTF_8.name());

                    if (result.equalsIgnoreCase("notfound")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Dieser Benutzer konnte nicht gefunden werden!", ButtonType.OK);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.OK) {
                            alert.close();
                        }
                    } else if (result.equalsIgnoreCase("wrongpw")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Falsches Passwort!", ButtonType.OK);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.OK) {
                            alert.close();
                        }
                    } else if (NumberUtils.isCreatable(result)) {
                        DigitalCookbook.instance.setData(usernameField.getText(), Integer.parseInt(result));
                        DigitalCookbook.instance.getFrameHandler().openMainMenu();
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
            }
        });

        registerButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openRegisterFrame());
    }
}
