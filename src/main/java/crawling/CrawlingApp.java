package crawling;

import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.interfaces.IProductExtractor;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;

/**
 * The only purpose of this class is to work with the Controller and combine and return all crawling
 * results into a response object that the controller sends as a HTTP response to the requesting client
 */
public class CrawlingApp {
    public CrawlingApp(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public CrawlingApp() {

    }

    private ILinkExtractor linkExtractor;
    private IPageRetriever pageRetriever;
    private IPageTracker pageTracker;
    private IProductExtractor productExtractor;

    private String baseAddress;

    public CrawlingResult crawlAllProducts() {
        CrawlingJob crawlingJob = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);

        crawlingJob.start();
        return null;
    }

    public CrawlingResult crawlProductsByName(String name) {

        return null;
    }

    public CrawlingJobData getCrawlingJobDataByID(int crawlingJobID) {

        return null;
    }
}
