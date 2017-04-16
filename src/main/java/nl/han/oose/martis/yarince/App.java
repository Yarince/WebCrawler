package nl.han.oose.martis.yarince;

import nl.han.oose.martis.yarince.crawler.Crawler;
import nl.han.oose.martis.yarince.crawler.ICrawler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;


public class App {

    public static void main(String[] args) throws IOException, SQLException {
        String url = "http://google.ml";
        ArrayList<ICrawler> crawlers = new ArrayList<>();

        ForkJoinPool p = new ForkJoinPool(10);
        p.invoke(new Crawler("http://www.youtube.nl",""));
    }
}
