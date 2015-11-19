package it.max.android.domusalberti.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class InternetUtils {
    private Properties properties = null;

    public InternetUtils() {}

    public InternetUtils(Properties properties) {
        this.properties = properties;
    }

    public String creaURLWebServer(Properties properties) {
        String URLWebServer = "http://" + properties.getProperty("webserverAddress")
                              + ":"     + properties.getProperty("webserverPort")
                              + "/"     + properties.getProperty("webserverSitePath");

        return(URLWebServer);
    }

    public String creaURLArduinoServer(Properties properties) {
        String URLArduinoServer = "http://" + properties.getProperty("arduinoAddress")
                                  + ":"     + properties.getProperty("arduinoPort")
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

    private String getResponse (String url) {
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
