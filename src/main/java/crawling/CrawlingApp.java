package crawling;

import crawling.extractors.JSoupLinkExtractor;
import crawling.extractors.JSoupPageRetriever;
import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.process.CrawlingJob;
import crawling.product.IProductExtractor;
import crawling.product.StandardProductExtractor;
import crawling.trackers.BfsPageTracker;

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
        linkExtractor = new JSoupLinkExtractor();
        pageRetriever = new JSoupPageRetriever();
        pageTracker = new BfsPageTracker();
        productExtractor = new StandardProductExtractor();

        CrawlingJob crawlingJob = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);

        crawlingJob.start(baseAddress, null);
        return crawlingJob.crawlingResult;
    }

    public CrawlingResult crawlProductsByName(String name) {
        linkExtractor = new JSoupLinkExtractor();
        pageRetriever = new JSoupPageRetriever();
        pageTracker = new BfsPageTracker();
        productExtractor = new StandardProductExtractor();

        CrawlingJob crawlingJob = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);


        crawlingJob.start(baseAddress, name);
        return crawlingJob.crawlingResult;
    }

    public CrawlingJobData getCrawlingJobDataByID(int crawlingJobID) {

        return null;
    }
}
