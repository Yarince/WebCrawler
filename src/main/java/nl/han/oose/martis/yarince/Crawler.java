package nl.han.oose.martis.yarince;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.RecursiveAction;

import static nl.han.oose.martis.yarince.Parser.Crawl;

/**
 * Project name: WebCrawler.
 * Created by Yarince on 12/04/2017.
 */
public class Crawler extends RecursiveAction {

    private final String LONG_URL;
    private final String CONTAINS;

    Crawler(String longUrl, String contains) {
        this.LONG_URL = longUrl;
        this.CONTAINS = contains;
    }

    public synchronized void processPage(String LongURL, String contains) {

        DB db = new DB();
        /*
         * check if the given URL is already in database. get useful information
         */
        if (db.exists(LongURL)) {
            System.out.println("Exists skipping .... " + LongURL);
            return;
        }
        Document doc = null;
        try {
            doc = Jsoup.connect(LongURL).timeout(5000).get();
            if (doc.text().contains("")) {
                System.out.println(LongURL);
                db.addUrl(LongURL); // add found url to database
//                ContentList.insertKey(LongURL, LongURL);
            }

            // get all links and recursively call the processPage method
            Elements questions = doc.select("a[href]");
            Crawl(doc);
            for (Element link : questions) {
                if (link.attr("href").contains(contains))
                    processPage(link.attr("abs:href"), contains);
            }
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage() +" skipping .... " + LongURL);
        }
    }

    @Override
    protected void compute() {
        processPage(LONG_URL, CONTAINS);
    }
}
