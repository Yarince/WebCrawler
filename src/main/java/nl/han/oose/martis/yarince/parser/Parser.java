package nl.han.oose.martis.yarince.parser;

import nl.han.oose.martis.yarince.DB;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Default
public class Parser implements IParser {
    @Override
    public synchronized void crawl(Document doc) {
        DB db = new DB();

        // get all links and recursively call the processPage method
        Elements questions = doc.select("marquee");
        for (Element element : questions) {
            db.addContent(doc.baseUri(), element.text());
        }
    }
}