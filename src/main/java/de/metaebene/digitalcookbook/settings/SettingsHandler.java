package de.metaebene.digitalcookbook.settings;

import de.metaebene.digitalcookbook.DigitalCookbook;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SettingsHandler {

    public SettingsHandler() {
        try {
            System.out.println("Loading settings!");
            loadSettings();
        } catch (IOException e) {
            System.err.println("Failed to load settings: ");
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        File settingsFile = new File(DigitalCookbook.instance.getDataDir(), "settings.json");

        if (settingsFile.exists())
            settingsFile.delete();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("darkMode", DigitalCookbook.instance.getFrameHandler().isDarkMode());

        try (FileWriter file = new FileWriter(settingsFile)) {
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadSettings() throws IOException {
        File settingsFile = new File(DigitalCookbook.instance.getDataDir(), "settings.json");

        if (!settingsFile.exists()) {
            saveSettings();
        }

        FileInputStream fileInputStream = new FileInputStream(settingsFile);
        String text = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

        JSONObject jsonObject = new JSONObject(text);

        DigitalCookbook.instance.getFrameHandler().setDarkMode(jsonObject.getBoolean("darkMode"));
    }
}
