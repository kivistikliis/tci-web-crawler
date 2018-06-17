package crawling.extractors;

import crawling.interfaces.ILinkExtractor;


/**
 * Implementation of ILinkExtractor
 */
public class JSoupLinkExtractor implements ILinkExtractor {
    /**
     * Extracts all links from the given HTML
     * @param page HTML string of given page
     * @param baseURL baseUrl needed to complete links that only represent a relative path
     * @return String array of links on the given page
     */
    @Override
    public String[] extractLinks(String page, String baseURL) {
        return null;
    }
}
