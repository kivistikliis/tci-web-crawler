package crawling.interfaces;

/**
 * Interface for retrieving HTML from a page
 */
public interface IPageRetriever {
    /**
     * Retrieves HTML String from a given URL
     * @param url URL to extract
     * @return HTML String version of the given URL content
     * @throws Exception which has to be handled from its caller class
     */
    String getHTMLPage(String url) throws Exception;
}
