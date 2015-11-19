package it.max.android.domusalberti.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.max.android.domusalberti.entity.ArduinoServerProperties;
import it.max.android.domusalberti.entity.WebServerProperties;

public class InternetUtils {
    private WebServerProperties webServerProperties;
    private ArduinoServerProperties arduinoServerProperties;

    public InternetUtils() {}

    public InternetUtils(String webserverAddress, String webserverPort, String webserverSitePath) {
        webServerProperties = new WebServerProperties();

        webServerProperties.setWebserverAddress(webserverAddress);
        webServerProperties.setWebserverPort(webserverPort);
        webServerProperties.setWebserverSitePath(webserverSitePath);
    }

    public InternetUtils(String arduinoAddress, String arduinoPort) {
        arduinoServerProperties = new ArduinoServerProperties();

        arduinoServerProperties.setArduinoAddress(arduinoAddress);
        arduinoServerProperties.setArduinoPort(arduinoPort);
    }

    public String creaURLWebServer() {
        String URLWebServer = "http://" + webServerProperties.getWebserverAddress()
                              + ":"     + webServerProperties.getWebserverPort()
                              + "/"     + webServerProperties.getWebserverSitePath();

        return(URLWebServer);
    }

    public String creaURLArduinoServer() {
        String URLArduinoServer = "http://" + arduinoServerProperties.getArduinoAddress()
                                  + ":"     + arduinoServerProperties.getArduinoPort()
                                  + "/index.htm?";

        return(URLArduinoServer);
    }

    private String readResponse (InputStream response) {
        BufferedReader r = new BufferedReader(new InputStreamReader(response));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("READRESPONSE: 'ERRORE'");
        }

        return(total.toString());
    }

    public String getResponse (String url) {
        String response = new String();
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            InputStream is = con.getInputStream();
            response = this.readResponse(is);
            con.disconnect();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("GETRESPONSE: 'ERRORE'");
        }

        return(response);
    }
}
