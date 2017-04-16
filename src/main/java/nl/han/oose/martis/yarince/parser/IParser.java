package nl.han.oose.martis.yarince.parser;

import org.jsoup.nodes.Document;

/**
 * Project name: WebCrawler.
 * Created by Yarince on 16/04/2017.
 */
public interface IParser {
    void crawl(Document doc);
}
