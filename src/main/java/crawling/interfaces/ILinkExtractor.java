package crawling.interfaces;

public interface ILinkExtractor {
    String[] extractLinks(String page, String baseURL);
}
