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
import crawling.trackers.DfsPageTracker;

/**
 * The only purpose of this class is to work with the Controller and combine and return all crawling
 * results into a response object that the controller sends as a HTTP response to the requesting client
 */
public class CrawlingApp {
    private ILinkExtractor linkExtractor;
    private IPageRetriever pageRetriever;
    private IPageTracker pageTracker;
    private IProductExtractor productExtractor;

    private String baseAddress;

    public CrawlingApp(String baseAddress) {
        this.baseAddress = baseAddress;
        linkExtractor = new JSoupLinkExtractor();
        pageRetriever = new JSoupPageRetriever();
        productExtractor = new StandardProductExtractor();
    }

    public CrawlingApp() {

    }

    public CrawlingResult crawlAllProducts(String name, String algorithm) {

        if(algorithm.toLowerCase().equals("bfs"))
            pageTracker = new BfsPageTracker();
        else if(algorithm.toLowerCase().equals("dfs"))
            pageTracker = new DfsPageTracker();

        CrawlingJob crawlingJob = new CrawlingJob(linkExtractor, pageRetriever, pageTracker, productExtractor);

        try {
            crawlingJob.start(baseAddress, name);
            CrawlingJobRepository.save(crawlingJob);
            return crawlingJob.crawlingResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CrawlingJobData getCrawlingJobDataByID(int crawlingJobID) {

        CrawlingJob crawlingJob = CrawlingJobRepository.getById(crawlingJobID);

        if(crawlingJob==null)
            return null;

        return crawlingJob.crawlingJobData;
    }
}
