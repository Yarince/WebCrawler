package nl.han.oose.martis.yarince;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Project name: WebCrawler.
 * Created by Yarince on 12/04/2017.
 */
public class Crawler {

    public static void processPage(String LongURL) throws SQLException, IOException {

        DB db = new DB();
        String shortUrl = LongURL.replaceFirst("^(http://www\\.|http://|www\\.)", "");
        /*
         * check if the given URL is already in database. get useful information
         */
//        if (ContentList.isContentInMap(LongURL)) {
//            return;
//        }
        Document doc = null;
        try {
            doc = Jsoup.connect(LongURL).timeout(50000).get();
            if (doc.text().contains("")) {
                System.out.println(LongURL);
                db.addUrl(LongURL);
//                ContentList.insertKey(LongURL, LongURL);
            }

            // get all links and recursively call the processPage method
            Elements questions = doc.select("a[href]");
            for (Element link : questions) {
                if (link.attr("href").contains(shortUrl))
                    processPage(link.attr("abs:href"));
            }

        } catch (Exception e) {
            System.out.println("skipping .... " + LongURL);
        }
    }
}
