package nl.han.oose.martis.yarince;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;


public class App {

    public static void main(String[] args) throws IOException, SQLException {
        String url = "http://google.ml";
        ArrayList<Crawler> crawlers = new ArrayList<>();

        ForkJoinPool p = new ForkJoinPool(10);
        p.invoke(new Crawler("http://www.youtube.nl",""));
    }
}
