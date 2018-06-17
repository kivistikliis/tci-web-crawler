package crawling.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawling.interfaces.ILinkExtractor;
import crawling.interfaces.IPageRetriever;
import crawling.interfaces.IPageTracker;
import crawling.models.CrawlingJobData;
import crawling.models.CrawlingResult;
import crawling.product.IProductExtractor;
import crawling.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrawlingJob {
    static int idCounter;
    int jobId;
    Gson json = new GsonBuilder().setPrettyPrinting().create();

    public CrawlingJob(ILinkExtractor linkExtractor, IPageRetriever pageRetriever, IPageTracker pageTracker, IProductExtractor productExtractor) {
        this.linkExtractor = linkExtractor;
        this.pageRetriever = pageRetriever;
        this.pageTracker = pageTracker;
        this.productExtractor = productExtractor;
        this.jobId = idCounter++;
    }

    private ILinkExtractor linkExtractor;
    private IPageRetriever pageRetriever;
    private IPageTracker pageTracker;
    private IProductExtractor productExtractor;


    public CrawlingResult start(String baseAddress) {

        // initialize pageTracker with beginning url
        pageTracker.addPages(Arrays.asList(baseAddress));

        // to gather all products
        List<Product> allProducts = new ArrayList<>();

        // loop through BFSTracker
        while (pageTracker.hasNext()) {
            try{
                String addressToAnalyse = pageTracker.getNext();

                if(addressToAnalyse.contains("facebook") || addressToAnalyse.contains("twitter"))
                    continue;

                // get HTML content of the given address
                String HTMLString = pageRetriever.getHTMLPage(addressToAnalyse);

                // get all the products
                ArrayList<Product> products = productExtractor.extractProducts(HTMLString);

                // get all the links and send to pageTracker
                String[] links = linkExtractor.extractLinks(HTMLString, addressToAnalyse);
                pageTracker.addPages(Arrays.asList(links));

                // store found product
                allProducts.addAll(products);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        int pagesExplored = pageTracker.getScannedPages().size();
        int timeSpentInMs = 0;
        int maxDepth = pageTracker.getMaximumScanDepth();
        CrawlingJobData crawlingJobData = new CrawlingJobData(jobId, timeSpentInMs, pagesExplored, maxDepth);
        CrawlingResult crawlingResult = new CrawlingResult(json.toJson(allProducts), jobId);

        return crawlingResult;
    }

}
