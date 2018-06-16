package crawling.interfaces;

import java.util.List;

/**
 * The interface is going to be implemented by one of the approaches: DFS or BFS, in order to
 * perform needed actions while crawling a page.
 */
public interface IPageTracker {

    /**
     * This method is destined to the addition of freshly found pages to the list of pages that will be scanned
     * in the future.
     * @param pages pages that will have to be scanned in the future
     */
    void addPages(List<String> pages);

    /**
     * This method is used to get the maximum reached depth during the scanning of pages.
     * @return maximum scan depth that was reached
     */
    int getMaximumScanDepth();

    /**
     * This method is used to provide the next page that needs to be scanned.
     * @return the next page to be scanned
     */
    String getNext();

    /**
     * This method provides the already scanned pages.
     * @return the scanned pages
     */
    List<String> getScannedPages();

    /**
     * This method is used to identify whether there is another page following in the list.
     * @return true if there is another page to be scanned and false if there is not
     */
    Boolean hasNext();
}
