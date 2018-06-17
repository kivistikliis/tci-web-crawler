package crawling.process;

import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.interfaces.IProductExtractor;

public class CrawlingJob {

    public CrawlingJob(ILinkExtractor linkExtractor, IPageRetriever pageRetriever, IPageTracker pageTracker, IProductExtractor productExtractor) {
        this.linkExtractor = linkExtractor;
        this.pageRetriever = pageRetriever;
        this.pageTracker = pageTracker;
        this.productExtractor = productExtractor;
    }

    private ILinkExtractor linkExtractor;
    private IPageRetriever pageRetriever;
    private IPageTracker pageTracker;
    private IProductExtractor productExtractor;


}
