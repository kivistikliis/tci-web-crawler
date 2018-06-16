package crawling.extractors;

import crawling.interfaces.IPageRetriever;

/**
 * Responsible for retrieving HTML from pages
 */
public class JSoupPageRetriever implements IPageRetriever {
    /**
     * Retrieves HTML String from a given URL
     * @param url URL to extract
     * @return HTML String version of the given URL content
     * @throws Exception which has to be handled by the crawling process manager class:
     *      IllegalArgumentException (if URL is null or empty),
     *      UnknownHostException (if site not reachable)
     */
    @Override
    public String getHTMLPage(String url) throws Exception {
        return null;
    }
}
