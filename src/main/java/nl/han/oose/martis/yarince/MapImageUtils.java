package nl.han.oose.martis.yarince;

/**
 * Project name: WebCrawler.
 * Created by Yarince on 13/04/2017.
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
//import java.util.Base64;

/**
 * @author saifasif
 */
public class MapImageUtils {

    private static final String URL = "http://maps.googleapis.com/maps/api/staticmap?";
    private static final String key = "AIzaSyAjpyprLWvShs8qFbsze_14kqbXQUGv3Y8";

    public static void main(String[] args) {
        try {
            getImageStream("51.826915", "5.872033");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String getImageStream(String latitude, String longitude) throws IOException {
        String urlString = URL + "center=" + latitude + "," + longitude;
        urlString += "&key=" + key;
        urlString += "&zoom=15&size=400x400";
        System.out.println(urlString);
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setUseCaches(false);
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.close();
        InputStream iSteamReader = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(iSteamReader));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        reader.close();
//        return Base64.getEncoder().encodeToString(
                return Arrays.toString(response.toString().getBytes("utf-8"));
    }
}