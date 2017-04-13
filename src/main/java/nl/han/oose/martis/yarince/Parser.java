package nl.han.oose.martis.yarince;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Parser {
    public synchronized static void Crawl(Document doc) {
        DB db = new DB();

        // get all links and recursively call the processPage method
        Elements questions = doc.select("marquee");
        for (Element element : questions) {
            db.addContent(doc.baseUri(), element.text());
        }
    }
}