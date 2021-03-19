package de.metaebene.digitalcookbook.web;

import de.metaebene.digitalcookbook.DigitalCookbook;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class WebHandler {

    public InputStream sendHTTPRequest(String url, List<BasicNameValuePair> params) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        return entity.getContent();
    }

    public boolean auth(String username, String password) {
        String result = null;
        try {
            result = IOUtils.toString(DigitalCookbook.instance.getWebHandler().sendHTTPRequest("http://fjg31.ddns.net/login.php", Arrays.asList(new BasicNameValuePair("username", username), new BasicNameValuePair("password", password))), StandardCharsets.UTF_8.name());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

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
            DigitalCookbook.instance.setData(username, Integer.parseInt(result));
            DigitalCookbook.instance.getFrameHandler().openMainMenu();

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fehlern bei der Kommunikation mit dem Server, schließe Programm!", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                System.exit(1);
            }
        }
        return false;
    }
}
