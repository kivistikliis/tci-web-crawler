package crawling.extractors;

import crawling.interfaces.ILinkExtractor;
import org.jsoup.Jsoup;

public class JSoupLinkExtractor implements ILinkExtractor {
    @Override
    public String[] extractLinks(String page, String baseURL) {
        return new String[0];
    }
}
