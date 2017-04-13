package nl.han.oose.martis.yarince;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

import static nl.han.oose.martis.yarince.Crawler.processPage;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException, SQLException {
            App app = new App();

            processPage("http://stackoverflow.com");

            Map<String, String> contentMap = ContentList.getContentMap();
            for (String url : contentMap.values()) {
                app.Crawl(url);
            }
    }

    private void Crawl(String URLString) {
        URL url;
        InputStream is = null;
        BufferedReader dis;
        String line;

        try {
            url = new URL(URLString);
            is = url.openStream();  // throws an IOException
            dis = new BufferedReader(new InputStreamReader(is));

            while ((line = dis.readLine()) != null) {
                if (line.contains(""))
                    System.out.println(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ignored) {
                // nothing to see here
            }
        }
    }

}
