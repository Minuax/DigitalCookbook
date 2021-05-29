package de.metaebene.digitalcookbook.gui.impl;

import de.metaebene.digitalcookbook.DigitalCookbook;
import de.metaebene.digitalcookbook.gui.FrameHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsFrameController implements Initializable {

    public CheckBox darkMode;

    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        darkMode.setSelected(DigitalCookbook.instance.getFrameHandler().isDarkMode());
        darkMode.setOnAction(event -> {
            DigitalCookbook.instance.getFrameHandler().setDarkMode(darkMode.isSelected());
            DigitalCookbook.instance.getFrameHandler().openFrame("Settings");
        });


        backButton.setOnAction(event -> DigitalCookbook.instance.getFrameHandler().openMainMenu());
    }
}
