package nl.han.oose.martis.yarince;


import java.util.HashMap;
import java.util.Map;

/**
 * Project name: WebCrawler.
 * Created by Yarince on 13/04/2017.
 */
public class ContentList {

    private static Map<String, String> instanceMap = new HashMap<>();

    private ContentList() {
    }

    /**
     * @return
     */
    public static Map<String, String> getContentMap() {
        return instanceMap;
    }

    /**
     * @param key
     * @return
     */
    public static boolean isContentInMap(String key) {
        return instanceMap.containsKey(key);
    }

    /**
     * @param k
     * @param v
     */
    public static void insertKey(String k, String v) {
        instanceMap.put(k, v);
    }

}
