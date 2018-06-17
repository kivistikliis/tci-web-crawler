package crawling;

import api.models.response.CrawlingJobData;
import api.models.response.CrawlingResult;

/**
 * The only purpose of this class is to work with the Controller and combine and return all crawling
 * results into a response object that the controller sends as a HTTP response to the requesting client
 */
public class CrawlingManager {
    public CrawlingManager(String baseAddress) {
        this.baseAddress = baseAddress;
    }
    public CrawlingManager() {

    }

    private String baseAddress;

    public CrawlingResult crawlAllProducts() {
        // Here we call tha actual CrawlingApp class

        return new CrawlingResult("test", 1);
    }

    public CrawlingResult crawlProductsByName(String name) {
        // Here we call tha actual CrawlingApp class

        return new CrawlingResult("test", 1);

    }

    public CrawlingJobData getCrawlingJobDataByID(int crawlingJobID) {
        // Here we call tha actual CrawlingApp class

        return new CrawlingJobData(1, 1, 1, 1);
    }
}
