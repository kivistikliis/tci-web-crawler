package crawling.interfaces;

/**
 * Interface to extract links from HTML content
 */
public interface ILinkExtractor {
    /**
     * Get all links from given page (HTML String)
     * @param page HTML content of page
     * @param baseURL URL from where links are retrieved
     * @return Returns String array containing all links
     */

    String[] extractLinks(String page, String baseURL);
}
